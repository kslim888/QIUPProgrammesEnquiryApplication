package entryRules;

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
    private boolean gotMathSubjectAndCredit, gotEnglishSubjectAndPass;

    public DIT() {
        ditRuleAttribute = new RuleAttribute();
        gotMathSubjectAndCredit = false;
        gotEnglishSubjectAndPass = false;
    }

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
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    ditRuleAttribute.incrementCountSPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O Level qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D7")
                            && !Objects.equals(studentGrades[i], "E8")
                            && !Objects.equals(studentGrades[i], "F9")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D7")
                        && !Objects.equals(studentGrades[i], "E8")
                        && !Objects.equals(studentGrades[i], "F9")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    ditRuleAttribute.incrementCountOLevel(1);
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
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level")) // if is O-Level
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D7")
                        && !Objects.equals(studentMathematicsGrade, "E8")
                        && !Objects.equals(studentMathematicsGrade, "F9")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D7")
                        && !Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check english
                if(!Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    ditRuleAttribute.incrementCountSTPM(1);
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
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level")) // if is O-Level
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D7")
                        && !Objects.equals(studentMathematicsGrade, "E8")
                        && !Objects.equals(studentMathematicsGrade, "F9")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D7")
                        && !Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check english
                if(!Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    ditRuleAttribute.incrementCountALevel(1);
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
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))

                {
                    gotMathSubjectAndCredit = true;
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level")) // if is O-Level
            {
                //check math
                if(!Objects.equals(studentMathematicsGrade, "D7")
                        && !Objects.equals(studentMathematicsGrade, "E8")
                        && !Objects.equals(studentMathematicsGrade, "F9")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }

                //check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D7")
                        && !Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }
            }

            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    ditRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    ditRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        if(ditRuleAttribute.getCountSTPM() >= 2 || ditRuleAttribute.getCountALevel() >= 2)
        {
            if(gotMathSubjectAndCredit && gotEnglishSubjectAndPass)
            {
                return true;
            }
        }
        if(ditRuleAttribute.getCountSPM() >= 3
                || ditRuleAttribute.getCountOLevel() >= 3
                || ditRuleAttribute.getCountSTAM() >= 1
                || ditRuleAttribute.getCountUEC() >= 3)
        {
            if(gotMathSubjectAndCredit)
            {
                return true;
            }
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        ditRuleAttribute.setJoinProgramme(true);
        Log.d("DiplomaIT", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return ditRuleAttribute.isJoinProgramme();
    }
}
