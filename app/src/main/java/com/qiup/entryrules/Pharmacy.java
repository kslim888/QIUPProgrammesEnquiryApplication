package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "Pharmacy", description = "Entry rule to join Bachelor of Pharmacy")
public class Pharmacy
{
    // Advanced math is additional maths
    private static RuleAttribute pharmacyRuleAttribute;

    public Pharmacy() { pharmacyRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's English") String studentEnglishGrade,
                               @Fact("Student's Bahasa Malaysia") String studentBahasaMalaysiaGrade
                               //@Fact("Student's English Proficiency Test Name") String studentEnglishProficiencyTestName,
                               /*@Fact("Student's English Proficiency Level") String studentEnglishProficiencyLevel*/)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            //check got bio chemi math physic or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)") || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    pharmacyRuleAttribute.setGotMathSubject();
                }
                if( Objects.equals(studentSubjects[i], "Fizik"))
                {
                    pharmacyRuleAttribute.setGotPhysics();
                }
                if( Objects.equals(studentSubjects[i], "Kimia"))
                {
                    pharmacyRuleAttribute.setGotChemi();
                }
                if( Objects.equals(studentSubjects[i], "Biology"))
                {
                    pharmacyRuleAttribute.setGotBio();
                }
            }

            // if no chemi and bio, straight return false
            // if no Physics and Mathematics, return false. If either 1 got, then continue...
            if(!pharmacyRuleAttribute.isGotChemi()
                    || !pharmacyRuleAttribute.isGotBio()
                    || (!pharmacyRuleAttribute.isGotMathSubject() && !pharmacyRuleAttribute.isGotPhysics()))
            {
                return false;
            }

            // For all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)")
                        || Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        pharmacyRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Kimia")
                        || Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        pharmacyRuleAttribute.incrementSTPMCredit();
                    }
                }
            }

            // If got enough credit,
            // Check SPM / o level BM and english got at least credit or not
            if(pharmacyRuleAttribute.getStpmCredit() >= 3)
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "G"))
                        {
                            // SPM BM and english got at least credit, check english proficiency level
                           // if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                            //{
                                return true;
                            //}
                        }
                    }
                }
                else if(Objects.equals(studentSPMOLevel, "O-Level"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "F")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "U"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "F")
                                && !Objects.equals(studentEnglishGrade, "G")
                                && !Objects.equals(studentEnglishGrade, "U"))
                        {
                            // o level BM and english got at least credit, check english proficiency level
                            //if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                            //{
                                return true;
                            //}
                        }
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check got bio chemi math or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    pharmacyRuleAttribute.setGotMathSubject();
                }
                if( Objects.equals(studentSubjects[i], "Physics"))
                {
                    pharmacyRuleAttribute.setGotPhysics();
                }
                if( Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    pharmacyRuleAttribute.setGotChemi();
                }
                if( Objects.equals(studentSubjects[i], "Biology"))
                {
                    pharmacyRuleAttribute.setGotBio();
                }
            }

            // if no chemi and bio, straight return false
            // if no Physics and Mathematics, return false
            if(!pharmacyRuleAttribute.isGotChemi()
                    || !pharmacyRuleAttribute.isGotBio()
                    || (!pharmacyRuleAttribute.isGotMathSubject() && !pharmacyRuleAttribute.isGotPhysics()))
            {
                return false;
            }

            // For all students subject check math, Bio, Chemi, Physics / Math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A*")
                            || Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "B")
                            || Objects.equals(studentGrades[i], "C"))
                    {
                        pharmacyRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A*")
                            || Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        pharmacyRuleAttribute.incrementALevelCredit();
                    }
                }
            }

            // if got enough credit, check SPM / o level BM and english got at least credit or not
            if(pharmacyRuleAttribute.getALevelCredit() >= 3)
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "G"))
                        {
                            // SPM BM and english got at least credit, check english proficiency level
                           // if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                            //{
                                return true;
                           // }
                        }
                    }
                }
                else if(Objects.equals(studentSPMOLevel, "O-Level"))
                {
                    if(!Objects.equals(studentBahasaMalaysiaGrade, "D")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "E")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "F")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "G")
                            && !Objects.equals(studentBahasaMalaysiaGrade, "U"))
                    {
                        if(!Objects.equals(studentEnglishGrade, "D")
                                && !Objects.equals(studentEnglishGrade, "E")
                                && !Objects.equals(studentEnglishGrade, "F")
                                && !Objects.equals(studentEnglishGrade, "G")
                                && !Objects.equals(studentEnglishGrade, "U"))
                        {
                            // o level BM and english got at least credit, check english proficiency level
                           // if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                           // {
                                return true;
                           // }
                        }
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all students subject check got mathematics, physic, chemi, bio subject or not
            // Add maths is advanced maths
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    pharmacyRuleAttribute.setGotAddMaths();
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    pharmacyRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    pharmacyRuleAttribute.setGotChemi();
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    pharmacyRuleAttribute.setGotBio();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    pharmacyRuleAttribute.setGotPhysics();
                }
            }

            // If either chemi bio physic maths or add maths no, return false
            if(!pharmacyRuleAttribute.isGotChemi()
                    || !pharmacyRuleAttribute.isGotBio()
                    || !pharmacyRuleAttribute.isGotPhysics()
                    || !pharmacyRuleAttribute.isGotMathSubject()
                    || !pharmacyRuleAttribute.isGotAddMaths())
            {
                return false;
            }

            // For all subject check other subject is at least B4 or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        pharmacyRuleAttribute.incrementUECCredit();
                    }
                }
            }

            if(pharmacyRuleAttribute.getUecCredit() >= 5)
            {
                return true;
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }
        // If requirements not satisfied, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        pharmacyRuleAttribute.setJoinProgrammeTrue();
        Log.d("Pharmacy", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return pharmacyRuleAttribute.isJoinProgramme();
    }

    private boolean isEnglishProficiencyPass(String studentEnglishProficiencyTestName, String studentEnglishProficiencyLevel)
    {
        double proficiencyNumber;
        if(Objects.equals(studentEnglishProficiencyTestName, "MUET"))
        {
            // At least band 3
            if(!Objects.equals(studentEnglishProficiencyLevel, "Band 2")
                    && !Objects.equals(studentEnglishProficiencyLevel, "Band 1"))
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "IELTS"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 6.0 )
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "TOEFL (Paper-Based Test)"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 550)
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "TOEFL (Internet-Based Test)"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 79)
            {
                return true;
            }
        }
        return false;
    }
}
