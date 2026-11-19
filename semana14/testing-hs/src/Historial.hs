module Historial (registrarConsulta, consultasDe) where

import Control.Monad.State (State, modify)
import qualified Data.Map as Map

registrarConsulta :: String -> State (Map.Map String Int) ()
registrarConsulta nombre = modify (Map.insertWith (+) nombre 1)

consultasDe :: String -> Map.Map String Int -> Int
consultasDe nombre = Map.findWithDefault 0 nombre
