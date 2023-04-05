<?php
  try{// NOK {{Avoid using try-catch-finally}}
        isNumber(2); // isNumber is a function which checks if argument is number
        echo "It's a number";
    }catch(CheckTypeException $exception){ // CheckTypeException personalized exception
        echo "Erreur:".$exception;
    }

  try{// NOK {{Avoid using try-catch-finally}}
        isNumber(2); // isNumber is a function which checks if argument is number
        echo "It's a number";
    }catch(CheckTypeException $exception){ // CheckTypeException personalized exception
        echo "Erreur:".$exception;
    }finally{
     echo "Finally.\n";
    }

if(isNumber(2)){
        echo "It's a number";
    }else{
        echo "It's not a number";
    }
?>
