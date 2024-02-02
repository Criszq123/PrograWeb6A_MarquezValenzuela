<?php

function imprimirDiamante($tamanio){
    if(is_numeric($tamanio) && $tamanio <= 0){
        echo "por favor ingrese un numero positivo como argumento";
        return;
    }
    for($i = 0; $i < $tamanio; $i++){
        echo str_repeat(" ", $tamanio - $i);
        echo str_repeat( "* ", $i);
        echo "\n";
        }
    for($i = $tamanio - 2; $i >= 0; $i--){
        echo str_repeat(" ", $tamanio - $i);
        echo str_repeat( "* ", $i);
        echo "\n";
    }
}


    if(isset($argv[1])){
        $tamanio = $argv[1];
        imprimirDiamante($tamanio);
    }
    else{
        echo "por favor ingrese un argumento";
    }
