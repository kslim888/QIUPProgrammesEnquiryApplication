package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DAC", description = "Entry rule to join Diploma in Accountancy")
public class DAC
{
    // advanced math is additional maths
    private static RuleAttribute dacRuleAttribute;

    public DAC() { dacRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if is SPM qualification
        {
            // Check maths got credit and english pass or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        dacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    if(Objects.equals(studentSubjects[i], "English"))
                    {
                        if(!Objects.equals(studentGrades[i], "G"))
                        {
                            dacRuleAttribute.setGotEnglishSubjectAndPass();
                        }
                    }
                }
            }

            // For all student's subject, check got C (credit) or not
            // Only C and above then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dacRuleAttribute.incrementSPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O Level qualification
        {
            // Check maths got credit and english pass or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics - Additional")
                        || Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics D")
                        || Objects.equals(studentSubjects[i], "International Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F")
                            && !Objects.equals(studentGrades[i], "G")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        dacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "U"))
                    {
                        dacRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // For all student's subject, check got C (credit) or not
            // Only C and above then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dacRuleAttribute.incrementOLevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            //check got credit in Mathematics and a pass in English at SPM / O-level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                // check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                // check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                // check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                // check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subject, check got grade C or not
            // Only C and above then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    dacRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A Level qualification
        {
            //check got credit in Mathematics and a pass in English at SPM / O-level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))

                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                // check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subjects, check got minimum grade C or not
            // Minimum grade C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dacRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check whether got credit in Mathematics and a pass in English at SPM / O-level
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level")) // if is O-Level
            {
                // check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    dacRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    dacRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    dacRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            //check got math and english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics") )
                {
                    dacRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "English") )
                {
                    dacRuleAttribute.setGotEnglishSubject();
                }
            }

            // If either 1 not exist, return false
            if(!dacRuleAttribute.isGotMathSubject() || !dacRuleAttribute.isGotEnglishSubject())
            {
                return false;
            }

            // For all student subjects, check mathematics is credit
            // and english is pass or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        dacRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        // Here pass means credit(at least B6 and above)
                        dacRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    dacRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        // For all the qualification, check got more than the credit or not
        // If more than, then check english is pass and math is credit or not
        // If both true, return true for all requirements is statisfied
        if(dacRuleAttribute.getSpmCredit() >= 3
                || dacRuleAttribute.getStamCredit() >= 1
                || dacRuleAttribute.getStpmCredit() >= 1
                || dacRuleAttribute.getALevelCredit() >= 1
                || dacRuleAttribute.getoLevelCredit() >= 3
                || dacRuleAttribute.getUecCredit() >= 3)
        {
            if(dacRuleAttribute.isGotEnglishSubjectAndPass() && dacRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // If requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If requirements is statisfied (return true), this action will be executed
        dacRuleAttribute.setJoinProgrammeTrue();
        Log.d("DiplomaInAccountancy", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dacRuleAttribute.isJoinProgramme();
    }
}
