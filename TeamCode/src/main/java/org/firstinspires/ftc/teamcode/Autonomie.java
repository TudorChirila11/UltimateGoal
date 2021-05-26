package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.SynchronousQueue;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
@Autonomous(name="prima autonomie")
public class Autonomie extends LinearOpMode {
    public DcMotor mfl, mfr, mbl, mbr, shooter, intake, rulment;
    public CRServo lift, push, wobble;
    public void hardwaremapping()
    {
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mfr = hardwareMap.get(DcMotor.class,"mfr");
        mbl = hardwareMap.get(DcMotor.class,"mbl");
        mbr = hardwareMap.get(DcMotor.class,"mbr");
        shooter=hardwareMap.get(DcMotor.class, "shooter");
        intake=hardwareMap.get(DcMotor.class, "intake");
        lift=hardwareMap.crservo.get("lift");
        push=hardwareMap.crservo.get("push");
        rulment=hardwareMap.get(DcMotor.class,"shooter");
        wobble=hardwareMap.crservo.get("wobble");
    }
    public void init1()
    {
        mfl.setDirection(DcMotorSimple.Direction.FORWARD);
        mfr.setDirection(DcMotorSimple.Direction.FORWARD);
        mbl.setDirection(DcMotorSimple.Direction.FORWARD);
        mbr.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        push.setDirection(DcMotorSimple.Direction.FORWARD);
        rulment.setDirection(DcMotorSimple.Direction.FORWARD);
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
        shooter.setPower(0);
        intake.setPower(0);
       // lift.setPower(0);
      //  push.setPower(0);
        rulment.setPower(0);
    }
    public void move_forward(int mseconds, double speed)
    {
        double x=0;
        double y=0;
        moveState(x,y,-1, speed);
        sleep(mseconds);
        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
    }
    public void shoot()
    {

    }
    double[] vectorToDCMotorsPower(double[] direction) throws ArithmeticException {
        double angle = Math.atan2(direction[1], direction[0]) * 57.2957795f; // Angle in Deg
        angle -= 45; // Rotate with 45 deg
        angle *= 0.0174532925f;
        double dlen=Math.sqrt(Math.abs(direction[0])*Math.abs(direction[0])+Math.abs(direction[1])*Math.abs(direction[1]));
        double[] ans={Math.cos(angle) * dlen, Math.sin((angle) * dlen)};
        return ans;
    }
    void moveState(double x, double y, double rotation, double speed) {
        double[] newv={x,y};
        double[] p = vectorToDCMotorsPower(newv);
        double fl1 = p[0] + rotation;
        double fr1 = p[1] - rotation;
        double bl1 = p[1] + rotation;
        double br1 = p[0] - rotation;
        //if (!activateMotorsAndServos)
        //  return;
        mfl.setPower(Range.clip(fl1, -1, 1) * speed);
        mfr.setPower(Range.clip(fr1, -1, 1) * speed);
        mbl.setPower(Range.clip(bl1, -1, 1) * speed);
        mbr.setPower(Range.clip(br1, -1, 1) * speed);
    }
    @Override
    public void runOpMode()
    {
        hardwaremapping();
        init1();
        waitForStart();
    //    move_forward(2500,0.8);
        shooter.setPower(1);
        sleep(6000);
        push.setPower(-1);
        sleep(300);
        push.setPower(1);
        sleep(300);
       // push.setPower(0);
        shooter.setPower(0);
        //move_forward(300,-1);
    }
}
