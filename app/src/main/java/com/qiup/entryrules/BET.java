package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BET", description = "Entry rule to join Bachelor of Environmental Technology")

public class BET
{
    //advanced math = additional math for UEC
    private static RuleAttribute betRuleAttribute;
    public BET() {
        betRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade,
                               @Fact("Student's Science/Technical/Vocational Subject") String studentScienceTechnicalVocationalSubject,
                               @Fact("Student's Science/Technical/Vocational Grade") String studentScienceTechnicalVocationalGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // check math and science got at least pass or not
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }

                if(Objects.equals(studentScienceTechnicalVocationalSubject, "Science")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry"))
                {
                    if(!Objects.equals(studentScienceTechnicalVocationalGrade, "D")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "E")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "G"))
                    {
                        betRuleAttribute.setGotScienceSubjectsCredit();
                    }
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }

                if(Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Science - Combined")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics Science")
                        || Objects.equals(studentScienceTechnicalVocationalSubject, "Sciences - Co-ordinated (Double)"))
                {
                    if(!Objects.equals(studentScienceTechnicalVocationalGrade, "D")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "E")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "F")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "G")
                            && !Objects.equals(studentScienceTechnicalVocationalGrade, "U"))
                    {
                        betRuleAttribute.setGotScienceSubjectsCredit();
                    }
                }
            }

            // for all students grade check got above at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    betRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check math got at least pass or not at SPM or O-Level
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    betRuleAttribute.setGotMathSubjectAndCredit();
                }
            }

            // for all students grade check got above at least Jayyid or not. At least Jayyid only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    betRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // Check math and sciences subject is credit or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                // Check math
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        betRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }

                //Check science
                if(Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        betRuleAttribute.setGotScienceSubjectsCredit();
                    }
                }
            }

            // for all students grade check got above at least or not.
            // At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    betRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject, check math is credit and sciences subject is credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                // Check math
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        betRuleAttribute.setGotMathSubjectAndCredit();
                        break;
                    }
                }
            }

            // Check science
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        betRuleAttribute.setGotScienceSubjectsCredit();
                        break;
                    }
                }
            }

            // for all students grade check got above at least B or not.
            // At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    betRuleAttribute.incrementUECCredit();
                }
            }
        }
        else
        {
            // TODO Matriculation, foundation, diploma
        }

        //If qualification is UEC, STPM, or A-Level, check enough credit or not
        if(betRuleAttribute.getUecCredit() >= 5 || betRuleAttribute.getStpmCredit() >= 2
                || betRuleAttribute.getALevelCredit() >= 2)
        {
            // if enough credit, check math is credit and sciences subject is credit or not
            if(betRuleAttribute.isGotMathSubjectAndCredit() && betRuleAttribute.isGotScienceSubjectsCredit())
            {
                return true;
            }
        }

        // If qualification is STAM, check enough credit or not
        if(betRuleAttribute.getStamCredit() >= 2)
        {
            // if enough credit, check math is credit or not
            if(betRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // if requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        betRuleAttribute.setJoinProgrammeTrue();
        Log.d("BET", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return betRuleAttribute.isJoinProgramme();
    }
}