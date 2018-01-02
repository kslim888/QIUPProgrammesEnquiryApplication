package entryRules;

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
    private boolean gotEnglishSubject, gotMathSubject, gotMathSubjectAndCredit, gotEnglishSubjectAndPass;

    public DAC() {
        dacRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotEnglishSubject = false;
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
        if(Objects.equals(qualificationLevel, "SPM")) // if is STPM qualification
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
                    if(Objects.equals(studentSubjects[i], "English"))
                    {
                        if(!Objects.equals(studentGrades[i], "G"))
                        {
                            gotEnglishSubjectAndPass = true;
                        }
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dacRuleAttribute.incrementCountSPM(1);
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
                if(Objects.equals(studentSubjects[i], "English Language")
                        || Objects.equals(studentSubjects[i], "Literature in English"))
                {
                    if(!Objects.equals(studentGrades[i], "E8")
                            && !Objects.equals(studentGrades[i], "F9")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        gotEnglishSubjectAndPass = true;
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
                    dacRuleAttribute.incrementCountOLevel(1);
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
                    gotMathSubjectAndCredit = true;
                }

                // check add math
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    gotMathSubjectAndCredit = true;
                }

                // check english
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                // check math
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
                if(!Objects.equals(studentEnglishGrade, "E8")
                        && !Objects.equals(studentEnglishGrade, "F9")
                        && !Objects.equals(studentEnglishGrade, "U"))
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
                    dacRuleAttribute.incrementCountSTPM(1);
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
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
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
                if(!Objects.equals(studentEnglishGrade, "E8")
                        && !Objects.equals(studentEnglishGrade, "F9")
                        && !Objects.equals(studentEnglishGrade, "U"))
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
                    dacRuleAttribute.incrementCountALevel(1);
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
                //check maths
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
                if(!Objects.equals(studentEnglishGrade, "E8")
                        && !Objects.equals(studentEnglishGrade, "F9")
                        && !Objects.equals(studentEnglishGrade, "U"))
                {
                    gotEnglishSubjectAndPass = true;
                }
            }

            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    dacRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            //check got math subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics") )
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "English") )
                {
                    gotEnglishSubject = true;
                }
            }

            if(!gotMathSubject || !gotEnglishSubject)
            {
                //TODO say error
                return false;
            }

            // for all student subjects, check the mathematics is got credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if( Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotEnglishSubjectAndPass = true; // here pass means credit(at least B6 and above)
                    }
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    dacRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        if(dacRuleAttribute.getCountSPM() >= 3
                || dacRuleAttribute.getCountSTAM() >= 1
                || dacRuleAttribute.getCountSTPM() >= 1
                || dacRuleAttribute.getCountALevel() >= 1
                || dacRuleAttribute.getCountOLevel() >= 3
                || dacRuleAttribute.getCountUEC() >= 3)
        {
            if(gotEnglishSubjectAndPass && gotMathSubjectAndCredit)
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
        dacRuleAttribute.setJoinProgramme(true);
        Log.d("DiplomaInAccountancy", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dacRuleAttribute.isJoinProgramme();
    }
}
