module Main (main) where

import Control.Monad.State (execState)
import qualified Data.Map as Map
import Historial
import Notas

lineas :: [String]
lineas = ["Ana,90", "Luis,45", "Marta,70", "lineamala"]

main :: IO ()
main = do
  let resultados = map parsearLinea lineas
      estudiantes = [ e | Right e <- resultados ]
  mapM_ (\err -> putStrLn ("Aviso: " ++ err)) [ err | Left err <- resultados ]
  putStrLn ("Promedio: " ++ show (promedio estudiantes))
  putStrLn ("Mas alto: " ++ show (masAlto estudiantes))
  putStrLn ("Ordenados: " ++ show (ordenarPorNota estudiantes))
  putStrLn ("Aprobados: " ++ show (aprobados estudiantes))

  let consultas = ["Ana", "Marta", "Ana", "Luis", "Ana"]
      historial = execState (mapM_ registrarConsulta consultas) Map.empty
  mapM_ (\(nombre, _) ->
          putStrLn ("Consultas de " ++ nombre ++ ": "
                    ++ show (consultasDe nombre historial)))
        estudiantes
