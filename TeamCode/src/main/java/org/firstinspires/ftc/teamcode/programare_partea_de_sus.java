package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous (name="programare partea de sus")
public class programare_partea_de_sus extends LinearOpMode{
    public CRServo push, lift;
    public DcMotor shooter, rulment;
    public void hardwaremapping()
    {
        shooter=hardwareMap.get(DcMotor.class, "shooter");
        rulment=hardwareMap.get(DcMotor.class, "rulment");
        lift=hardwareMap.crservo.get("lift");
        push=hardwareMap.crservo.get("push");
    }
    public void init1()
    {
        //  mfl.setDirection(DcMotorSimple.Direction.FORWARD);
        // mfr.setDirection(DcMotorSimple.Direction.FORWARD);
        // mbl.setDirection(DcMotorSimple.Direction.FORWARD);
        // mbr.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        //intake.setDirection(DcMotorSimple.Direction.FORWARD);
       // lift.setDirection(DcMotorSimple.Direction.FORWARD);
        push.setDirection(DcMotorSimple.Direction.FORWARD);
        rulment.setDirection(DcMotorSimple.Direction.FORWARD);
        //mfl.setPower(0);
        //mfr.setPower(0);
        // mbl.setPower(0);
        //mbr.setPower(0);
        shooter.setPower(0);
        // intake.setPower(0);
     //   lift.setPower(0);
        push.setPower(0);
        rulment.setPower(0);
    }
    @Override
    public void runOpMode()
    {
        hardwaremapping();
        init1();
        waitForStart();
       // shooter.setPower(-1);
       // sleep(5000);
        telemetry.addData("shooter",shooter.getPower());
        telemetry.addData("push",push.getPower());
        telemetry.addData("lift",lift.getPower());
       for(int i=1;i<=3;++i)
        {
            push.setPower(-1);
            sleep(300);
            push.setPower(1);
            sleep(300);
//            sleep(300);
        }
       // shooter.setPower(0);
    }
}
