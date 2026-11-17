module Main where

import Lib (suma)

main :: IO ()
main = do
  putStrLn "Ingresa el primer numero:"
  linea1 <- getLine
  putStrLn "Ingresa el segundo numero:"
  linea2 <- getLine
  let a = read linea1 :: Int
  let b = read linea2 :: Int
  putStrLn ("La suma es: " ++ show (suma a b))
