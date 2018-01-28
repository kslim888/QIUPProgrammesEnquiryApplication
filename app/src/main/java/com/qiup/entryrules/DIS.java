package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DIS", description = "Entry rule to join Diploma in Business Information System")
public class DIS
{
    private static RuleAttribute disRuleAttribute;

    public DIS() { disRuleAttribute = new RuleAttribute(); }

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
            // Check math subject got credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        disRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student's subject, check got minimum grade C or not
            // If is minimum grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    disRuleAttribute.incrementSPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O Level qualification
        {
            // Check math subject got credit or not
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
                        disRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student's subject, check got minimum grade C or not
            // If is minimum grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    disRuleAttribute.incrementOLevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // check whether got credit in Mathematics and English pass at SPM / O-level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    disRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    disRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subject, check got minimum grade C or not
            // If is minimum grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    disRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check whether got credit in Mathematics and English pass at spm / o level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    disRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    disRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subject, check got minimum grade C or not
            // If is minimum grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    disRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check whether got credit in Mathematics at SPM / O Level
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))

                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level")) // if is O-Level
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    disRuleAttribute.setGotMathSubjectAndCredit();
                }
            }

            // Minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    disRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        disRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    disRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        // If is STPM or A-Level, check credit is at least 2 or not
        // If is more than 2 , check math credit and english pass or not
        // If both true, return true for all requirements satisfy
        if(disRuleAttribute.getStpmCredit() >= 2 || disRuleAttribute.getALevelCredit() >= 2)
        {
            if(disRuleAttribute.isGotMathSubjectAndCredit() && disRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }

        // If is credit is enough, check math is credit or not
        // If both is true, return true for all requirements satisfy
        if(disRuleAttribute.getSpmCredit() >= 3
                || disRuleAttribute.getoLevelCredit() >= 3
                || disRuleAttribute.getStamCredit() >= 1
                || disRuleAttribute.getUecCredit() >= 3)
        {
            if(disRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        //if all requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        disRuleAttribute.setJoinProgrammeTrue();
        Log.d("DiplomaBusiInfoSystem", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return disRuleAttribute.isJoinProgramme();
    }
}
