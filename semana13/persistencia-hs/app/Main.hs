module Main (main) where

import Data.Either (lefts, rights)
import System.IO (hSetBuffering, stdout, BufferMode(..))
import Modelo
import Persistencia

archivo :: FilePath
archivo = "estudiantes.csv"

mostrar :: Estudiante -> String
mostrar e = nombre e ++ " - " ++ show (nota e)
  ++ (if esAprobado e then " (aprobado)" else " (reprobado)")

listar :: [Estudiante] -> IO ()
listar [] = putStrLn "No hay estudiantes."
listar es = do
  mapM_ (putStrLn . mostrar) es
  putStrLn ("Promedio: " ++ show (promedio es))

bucle :: [Estudiante] -> IO ()
bucle es = do
  putStrLn "\n1) Listar  2) Agregar  3) Salir"
  putStr "Opcion: "
  op <- getLine
  case op of
    "1" -> listar es >> bucle es
    "2" -> do
      putStr "Nombre: "
      n <- getLine
      putStr "Nota: "
      t <- getLine
      case parsearLinea (n ++ "," ++ t) of
        Left err -> putStrLn ("Error: " ++ err) >> bucle es
        Right e -> do
          let nuevos = es ++ [e]
          guardarEstudiantes archivo nuevos
          putStrLn "Estudiante guardado."
          bucle nuevos
    "3" -> putStrLn "Hasta luego."
    _   -> putStrLn "Opcion invalida." >> bucle es

main :: IO ()
main = do
  hSetBuffering stdout NoBuffering
  resultados <- cargarEstudiantes archivo
  mapM_ (\err -> putStrLn ("Aviso: " ++ err)) (lefts resultados)
  let validos = rights resultados
  putStrLn ("Cargados " ++ show (length validos) ++ " estudiantes.")
  bucle validos
