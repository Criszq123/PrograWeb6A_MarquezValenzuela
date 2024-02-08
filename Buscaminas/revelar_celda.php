<?php
 //revelar celda.php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $data = json_decode(file_get_contents('php://input'));
    $filas = $data->fila;
    $columnas = $data->columna;

    session_start();
    $tablero = $_SESSION['tablero'];

    echo json_encode([
        'valor' => $tablero[$filas][$columnas],
    ]);
}