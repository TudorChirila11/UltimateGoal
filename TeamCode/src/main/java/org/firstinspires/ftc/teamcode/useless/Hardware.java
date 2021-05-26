package org.firstinspires.ftc.teamcode.useless;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
public class Hardware
{
    /* Public OpMode members. */
    public DcMotor mfl;
    public DcMotor mfr;
    public DcMotor mbl;
    public DcMotor mbr;

    /* local OpMode members. */


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap HMap) {
        mfl = HMap.get(DcMotor.class, "mfl");
        mfr = HMap.get(DcMotor.class, "mfr");
        mbl = HMap.get(DcMotor.class, "mbl");
        mbr = HMap.get(DcMotor.class, "mbr");

        mfl.setPower(0);
        mfr.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);

        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        mfl.setDirection(DcMotor.Direction.REVERSE);
        mbl.setDirection(DcMotor.Direction.REVERSE);

        mfl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mfr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mbl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mbr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
 }

