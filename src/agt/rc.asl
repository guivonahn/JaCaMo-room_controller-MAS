+!adjust_temp
   : temperature(X) & desired_temp(Y) & X < Y
   <- aumentar;
    !adjust_temp.

+!adjust_temp
   : temperature(X) & desired_temp(Y) & X > Y
   <- diminuir;
      !adjust_temp.

+temperature(X)
 : desired_temp(Y) & X = Y
 <- parar.

{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }