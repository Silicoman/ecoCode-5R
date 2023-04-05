<?php

$a = [1, 2, 3, 17];

    function testForEachLoop() {
        $dummy = 0;
        foreach ($a as $v) { // NOK {{Avoid using arrays in foreach loops}}
            $dummy += $v;
        }
    }

    function testForLoop() {
        $dummy = 0;
        for ($i=0; $i < sizeof($a); ++$i) {
            $dummy += $a[$i];
        }
    }

?>