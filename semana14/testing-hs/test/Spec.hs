module Main (main) where

import System.Exit (exitFailure, exitSuccess)
import Test.HUnit
import Test.QuickCheck (quickCheck)
import Notas

testPromedioSimple :: Test
testPromedioSimple = TestCase $
  assertEqual "promedio de [10,20,30]" 20.0
    (promedio [("A", 10), ("B", 20), ("C", 30)])

testPromedioVacio :: Test
testPromedioVacio = TestCase $ 0.0 @?= promedio []

testMasAlto :: Test
testMasAlto = TestCase $
  Just ("B", 90) @?= masAlto [("A", 50), ("B", 90), ("C", 70)]

testAprobados :: Test
testAprobados = TestCase $
  [("A", 80), ("C", 51)] @?= aprobados [("A", 80), ("B", 50), ("C", 51)]

testParsearLineaValida :: Test
testParsearLineaValida = TestCase $
  Right ("Ana", 90.0) @?= parsearLinea "Ana,90"

testParsearLineaInvalida :: Test
testParsearLineaInvalida = TestCase $
  case parsearLinea "lineamala" of
    Left _  -> return ()
    Right _ -> assertFailure "se esperaba Left para una linea mal formada"

propOrdenarPreservaLongitud :: [Estudiante] -> Bool
propOrdenarPreservaLongitud xs =
  length (ordenarPorNota xs) == length xs

todosLosTests :: Test
todosLosTests = TestList
  [ TestLabel "promedio simple" testPromedioSimple
  , TestLabel "promedio vacio" testPromedioVacio
  , TestLabel "mas alto" testMasAlto
  , TestLabel "aprobados" testAprobados
  , TestLabel "parsear linea valida" testParsearLineaValida
  , TestLabel "parsear linea invalida" testParsearLineaInvalida
  ]

main :: IO ()
main = do
  resultados <- runTestTT todosLosTests
  putStrLn "Propiedad QuickCheck: ordenar preserva longitud"
  quickCheck propOrdenarPreservaLongitud
  if errors resultados + failures resultados == 0
    then exitSuccess
    else exitFailure
