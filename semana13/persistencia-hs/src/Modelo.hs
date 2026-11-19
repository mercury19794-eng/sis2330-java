module Modelo (Estudiante(..), esAprobado, promedio) where

data Estudiante = Estudiante
  { nombre :: String
  , nota   :: Double
  } deriving (Show, Eq)

esAprobado :: Estudiante -> Bool
esAprobado e = nota e >= 51

promedio :: [Estudiante] -> Double
promedio [] = 0
promedio es = sum (map nota es) / fromIntegral (length es)
