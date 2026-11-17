module Lib (suma, doble, promedio) where

-- Suma dos enteros
suma :: Int -> Int -> Int
suma a b = a + b

-- Devuelve el doble de un entero
doble :: Int -> Int
doble x = x * 2

-- Promedio de una lista de numeros (0 si la lista esta vacia)
promedio :: [Double] -> Double
promedio [] = 0
promedio xs = sum xs / fromIntegral (length xs)
