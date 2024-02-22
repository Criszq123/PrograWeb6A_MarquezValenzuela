 <?php
include 'Usuario.php';

$usuarioDAO = new UsuarioDAO();

$bugs = new Usuario();
$bugs->setNombres('Bugs');
$bugs->setApellidos('Bunny');
$bugs->setCorreo('bugsbunny@wb.com');
$bugs->guardar();

$lola = new Usuario();
$lola->setNombres('Lola');
$lola->setApellidos('Bunny');
$lola->setCorreo('lolabunny@wb.com');
$lola->guardar();

$lucas = new Usuario();
$lucas->setNombres('Daffy');
$lucas->setApellidos('Duck');
$lucas->setCorreo('patolucas@wb.com');
$lucas->guardar();

$porki = new Usuario();
$porki->setNombres('Porky');
$porki->setApellidos('Pig');
$porki->setCorreo('porkypig@wb.com');
$porki->guardar();


$usuarios = $usuarioDAO->buscarTodos();
foreach ($usuarios as $usuario) {
    echo $usuario->getNombres() . " " . $usuario->getApellidos() . " " . $usuario->getCorreo() . "<br>";
}

echo '<br>';

$porki->setCorreo('porkypig@wb.com');
$porki->actualizar();

$bugs->eliminar();

$usuarios = $usuarioDAO->buscarTodos();
foreach ($usuarios as $usuario) {
    echo $usuario->getNombres() . " " . $usuario->getApellidos() . " " . $usuario->getCorreo() . "<br>";
}