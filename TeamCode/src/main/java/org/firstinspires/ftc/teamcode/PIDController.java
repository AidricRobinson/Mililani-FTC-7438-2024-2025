package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.teamcode.Constants;

public class PIDController {
    private double error;
    private double kP, kI, kD, kFF, kIL;
    private double setPoint;
    private double processVariable;
    private double previousError;
    private double output, integral, derivative;

    public PIDController(double kP, double kI, double kD, double kFF, double kIL){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kFF = kFF;
        this.kIL = kIL;

    }
    public void createSetPoint (double setPoint){
        this.setPoint = setPoint;
        integral = 0;
        previousError = 0;
    }
    public void setProcessVariable(double processVariable) {
        this.processVariable = processVariable;
    }
    public double getError(){
        return setPoint - processVariable;
    }

    public double getOutput(){
        error = getError();
        integral += error;
        derivative = error - previousError;
        output = kP * error
                // + (integral > kIL ? 0 : kI * integral)
                + Math.copySign(kFF, error);
                // + kD * derivative;
        return output;
    }
}
