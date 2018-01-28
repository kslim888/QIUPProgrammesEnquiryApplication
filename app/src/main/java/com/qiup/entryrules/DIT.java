package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DIT", description = "Entry rule to join Diploma in Information Technology")
public class DIT
{
    private static RuleAttribute ditRuleAttribute;

    public DIT() { ditRuleAttribute = new RuleAttribute();}

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
            // Check math got credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        ditRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student's subject, check got above C (credit) or not
            // Only C or above increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    ditRuleAttribute.incrementSPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O Level qualification
        {
            // Check math got credit or not
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
                        ditRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student's subject, check got above C (credit) or not
            // Only C or above increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    ditRuleAttribute.incrementOLevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // check whether got credit in Mathematics and English pass at SPM / O-level
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    ditRuleAttribute.setGotEnglishSubjectAndPass();
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
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    ditRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subject, check got above C (credit) or not
            // Only C or above increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    ditRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check whether got credit in Mathematics and English pass at spm / o level
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    ditRuleAttribute.setGotEnglishSubjectAndPass();
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
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    ditRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student's subject, check got above C (credit) or not
            // Only C or above increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    ditRuleAttribute.incrementALevelCredit();
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
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))

                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
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
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    ditRuleAttribute.setGotMathSubjectAndCredit();
                }
            }

            // Minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    ditRuleAttribute.incrementSTAMCredit();
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
                        ditRuleAttribute.setGotMathSubjectAndCredit();
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
                    ditRuleAttribute.incrementUECCredit();
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
        if(ditRuleAttribute.getStpmCredit() >= 2 || ditRuleAttribute.getALevelCredit() >= 2)
        {
            if(ditRuleAttribute.isGotMathSubjectAndCredit() && ditRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }

        // if is SPM , O-Level, STAM, or UEC, check got enough credit or not
        // If is credit enough, check math is credit
        // If both true, return true for all requirements satisfy
        if(ditRuleAttribute.getSpmCredit() >= 3
                || ditRuleAttribute.getoLevelCredit() >= 3
                || ditRuleAttribute.getStamCredit() >= 1
                || ditRuleAttribute.getUecCredit() >= 3)
        {
            if(ditRuleAttribute.isGotMathSubjectAndCredit())
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
        // if rule is statisfied (return true), then this action will be executed
        ditRuleAttribute.setJoinProgrammeTrue();
        Log.d("DiplomaIT", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return ditRuleAttribute.isJoinProgramme();
    }
}
