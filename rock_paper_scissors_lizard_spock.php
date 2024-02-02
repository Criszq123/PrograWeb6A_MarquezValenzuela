<?php

    function juego_sheldon($jugador1, $jugador2) {
        $jugadores = array("Piedra", "Papel", "Tijeras", "Lagarto", "Spock");
        $victorias = array(

            0 => array(1, 3), // tijeras gana a papel y lagarto
            1 => array(2, 4), // papel gana a piedra y spock
            2 => array(3, 0), // piedra gana a tijeras y lagarto
            3 => array(4, 1), // lagarto gana a papel y spock
            4 => array(0, 2) // spock gana a piedra y tijeras
        );

        echo "El jugador 1 acaba de escoger: " . $jugadores[$jugador1]; // imprime la eleccion del jugador 1
        echo "\n";
        echo "El jugador 2 acaba de escoger: " . $jugadores[$jugador2]; // imprime la eleccion del jugador 2
        echo "\n";
        
        if ($jugador1 === $jugador2) { // en caso de ser empate
            echo"Empate";
        }

        if (in_array($jugador1, $victorias[$jugador2])) {
            echo "Gana el jugador 1";
        } else {
            echo "Gana el jugador 2";
        }
    }

    $jugador1 = intval($argv[1]); // toma los valores ingresados en la consola
    $jugador2 = intval($argv[2]);

    if ($jugador1 < 0 || $jugador1 > 4 || $jugador2 < 0 || $jugador2 > 4) { // verifica que los valores ingresados sean entre 0 y 4 * condicional creada con codemium *
        echo "Ingrese valores entre 0 y 4";
        return;
    }

   juego_sheldon($jugador1, $jugador2);
