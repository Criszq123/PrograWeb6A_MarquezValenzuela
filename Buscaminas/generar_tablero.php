<?php

//generar tablero

    class buscaminas{
        private $filas;
        private $columnas;
        private $minas;

        
        public function __construct($nivel){
            switch($nivel){
                case 'facil':
                    $this->filas = 8;
                    $this->columnas = 8;
                    $this->minas = 5;
                break;
                case 'medio':
                    $this->filas = 16;
                    $this->columnas = 16;
                    $this->minas = 15;
                break;
                case 'dificil':
                    $this->filas = 16;
                    $this->columnas = 30;
                    $this->minas = 20;
                break;
                default:
                // opcion por defecto si el nivel no es valido
                $this->filas = 8;
                $this->columnas = 8;
                $this->minas = 10;
            }
        }

        public function generarTablero(){
            // Inicializar el Tablero
            $tablero = array_fill(0, $this->filas, array_fill(0, $this->columnas, 0));

            //colocar las minas en posiciones aleatorias
            for($i = 0; $i < $this->minas; $i++){
                do{
                    $filas = rand(0, $this->filas - 1);
                    $columnas = rand(0, $this->columnas - 1);
                } while($tablero[$filas][$columnas] == -1);


                $tablero[$filas][$columnas] = -1;
                //Incrementar los valores alrededor de la mina 
                for($j = $filas - 1; $j <= $filas + 1; $j++){
                    for($k = $columnas - 1; $k <= $columnas + 1; $k++){
                        if($j >= 0 && $j < $this->filas && $k >= 0 && $k < $this->columnas && $tablero[$j][$k] != -1){
            
                                $tablero[$j][$k]++;
                            }
                        }
                    }
                }
                return $tablero;
            }
            
        }
        if( $_SERVER['REQUEST_METHOD'] == 'POST'){
            $data = json_decode(file_get_contents('php://input'));
            $nivel = $data->nivel;

            $buscaminas = new buscaminas($nivel);
            $tablero = $buscaminas->generarTablero();

            //guardar el tablero en una sesion para que este disponible para en el siguiente script
            session_start();
            $_SESSION['tablero'] = $tablero;

            echo json_encode([
                'nivel' => $nivel,
                'table' => $tablero,
            ]);
        }

        
        
        
    