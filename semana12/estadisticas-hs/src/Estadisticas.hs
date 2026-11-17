module Estadisticas
  ( Estudiante
  , promedio
  , masAlto
  , ordenarPorNota
  , aprobados
  ) where

import Data.List (maximumBy, sortBy)
import Data.Ord (Down (..), comparing)

-- Un estudiante es una tupla (nombre, nota)
type Estudiante = (String, Double)

-- Promedio de las notas (0 si la lista esta vacia)
promedio :: [Estudiante] -> Double
promedio [] = 0
promedio es = sum (map snd es) / fromIntegral (length es)

-- Estudiante con la nota mas alta (no usar con lista vacia)
masAlto :: [Estudiante] -> Estudiante
masAlto = maximumBy (comparing snd)

-- Ordena de mayor a menor nota
ordenarPorNota :: [Estudiante] -> [Estudiante]
ordenarPorNota = sortBy (comparing (Down . snd))

-- Estudiantes con nota mayor o igual a 51
aprobados :: [Estudiante] -> [Estudiante]
aprobados es = [e | e@(_, nota) <- es, nota >= 51]
