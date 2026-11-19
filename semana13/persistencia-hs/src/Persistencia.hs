module Persistencia
  ( parsearLinea
  , formatearLinea
  , guardarEstudiantes
  , cargarEstudiantes
  ) where

import System.Directory (doesFileExist)
import Text.Read (readMaybe)
import Modelo

separarComa :: String -> [String]
separarComa s = case break (== ',') s of
  (campo, [])      -> [campo]
  (campo, _:resto) -> campo : separarComa resto

validarNombre :: String -> Either String String
validarNombre n
  | null n    = Left "El nombre esta vacio"
  | otherwise = Right n

validarNota :: String -> Either String Double
validarNota t = case readMaybe t of
  Nothing -> Left ("La nota no es un numero: " ++ t)
  Just x
    | x < 0 || x > 100 -> Left ("Nota fuera de rango (0-100): " ++ t)
    | otherwise        -> Right x

parsearLinea :: String -> Either String Estudiante
parsearLinea linea = case separarComa linea of
  [n, t] -> do
    nombreOk <- validarNombre n
    notaOk   <- validarNota t
    Right (Estudiante nombreOk notaOk)
  campos -> Left ("Linea mal formada, se esperaban 2 campos y hay "
                  ++ show (length campos) ++ ": " ++ linea)

formatearLinea :: Estudiante -> String
formatearLinea e = nombre e ++ "," ++ show (nota e)

guardarEstudiantes :: FilePath -> [Estudiante] -> IO ()
guardarEstudiantes ruta estudiantes =
  writeFile ruta (unlines (map formatearLinea estudiantes))

cargarEstudiantes :: FilePath -> IO [Either String Estudiante]
cargarEstudiantes ruta = do
  existe <- doesFileExist ruta
  if not existe
    then return []
    else do
      contenido <- readFile ruta
      let lineasNoVacias = filter (not . null) (lines contenido)
      length contenido `seq` return (map parsearLinea lineasNoVacias)
