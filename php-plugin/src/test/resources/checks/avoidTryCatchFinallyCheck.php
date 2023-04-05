<?php
function inverse($x) {
    if (!$x) {
        throw new Exception('Division par zéro.');
    }
    return 1/$x;
}

try {
    echo inverse(1) . "\n";
} catch (Exception $e) {
    echo 'Exception reçue : ',  $e->getMessage(), "\n";
} finally { // NOK {{Avoid using try-catch-finally}}
    echo "Première fin.\n";
}

try {
    echo inverse(2) . "\n";
} catch (Exception $e) {
    echo 'Exception reçue : ',  $e->getMessage(), "\n";
}

