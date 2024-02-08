<?php

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
            }
        }
        
    }