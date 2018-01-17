package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DME", description = "Entry rule to join Diploma in Mechatronics Engineering")
public class DME
{
    //advanced math = additional math for UEC
    private static RuleAttribute dmeRuleAttribute;

    public DME() { dmeRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade,
                               @Fact("Student's English") String studentEnglishGrade,
                               @Fact("Student's Science/Technical/Vocational Subject") String studentScienceTechnicalVocationalSubject,
                               @Fact("Student's Science/Technical/Vocational Grade") String studentScienceTechnicalVocationalGrade)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if is SPM qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        dmeRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "G"))
                    {
                        dmeRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }

                //check Science/Technical/Vocational subject got pass or not
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Sains Pertanian")
                        || Objects.equals(studentSubjects[i], "Ekonomi Rumah Tangga")
                        || Objects.equals(studentSubjects[i], "Lukisan Kejuruteraan")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Mekanikal")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Awam")
                        || Objects.equals(studentSubjects[i], "Pengajian Kejuruteraan Elektrik dan Elektronik")
                        || Objects.equals(studentSubjects[i], "Reka Cipta")
                        || Objects.equals(studentSubjects[i], "Teknologi Kejuruteraan")
                        || Objects.equals(studentSubjects[i], "Grafik Komunikasi Teknikal")
                        || Objects.equals(studentSubjects[i], "Pembinaan Domestik")
                        || Objects.equals(studentSubjects[i], "Membuat Perabot")
                        || Objects.equals(studentSubjects[i], "Kerja Paip Domestik")
                        || Objects.equals(studentSubjects[i], "Pendawaian Domestik")
                        || Objects.equals(studentSubjects[i], "Kimpalan Arka dan Gas")
                        || Objects.equals(studentSubjects[i], "Menservis Automobil")
                        || Objects.equals(studentSubjects[i], "Menservis Motosikal")
                        || Objects.equals(studentSubjects[i], "Menservis Peralatan Penyejukan dan Penyamanan Udara")
                        || Objects.equals(studentSubjects[i], "Menservis Peralatan Elektrik Domestik")
                        || Objects.equals(studentSubjects[i], "Rekaan dan Jahitan Pakaian")
                        || Objects.equals(studentSubjects[i], "Katering dan Penyajian")
                        || Objects.equals(studentSubjects[i], "Pemprosesan Makanan")
                        || Objects.equals(studentSubjects[i], "Penjagaan Muka dan Dandanan Rambut")
                        || Objects.equals(studentSubjects[i], "Asuhan dan Pendidikan Awal Kanak-Kanak")
                        || Objects.equals(studentSubjects[i], "Gerontologi Asas dan Perkhidmatan Geriatrik")
                        || Objects.equals(studentSubjects[i], "Landskap dan Nurseri")
                        || Objects.equals(studentSubjects[i], "Akuakultur dan Haiwan Rekreasi")
                        || Objects.equals(studentSubjects[i], "Tanaman Makanan")
                        || Objects.equals(studentSubjects[i], "Seni Reka Tanda")
                        || Objects.equals(studentSubjects[i], "Hiasan Dalaman Asas")
                        || Objects.equals(studentSubjects[i], "Produksi Multimedia")
                        || Objects.equals(studentSubjects[i], "Grafik Berkomputer"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dmeRuleAttribute.incrementCountSPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O-Level qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics D"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F")
                            && !Objects.equals(studentGrades[i], "G")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        dmeRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F")
                            && !Objects.equals(studentGrades[i], "G")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        dmeRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English Language")
                        || Objects.equals(studentSubjects[i], "English - First Language") )
                {
                    if(!Objects.equals(studentGrades[i], "U"))
                    {
                        dmeRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }

                //check Science/Technical/Vocational subject got pass or not
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Agriculture")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Environmental Management")
                        || Objects.equals(studentSubjects[i], "Food and Nutrition")
                        || Objects.equals(studentSubjects[i], "Marine Science")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Science - Combined")
                        || Objects.equals(studentSubjects[i], "Physical Education")
                        || Objects.equals(studentSubjects[i], "Physics Science")
                        || Objects.equals(studentSubjects[i], "Sciences - Co-ordinated (Double)"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "F")
                            && !Objects.equals(studentGrades[i], "G")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dmeRuleAttribute.incrementCountOLevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // check math and eng got pass or not
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    dmeRuleAttribute.setGotEnglishSubjectAndPass();
                }

                if(!Objects.equals(studentMathematicsGrade, "G"))
                {
                    dmeRuleAttribute.setGotMathSubjectAndPass();
                }

                // Here scienceTechnicalVocationalSubjectsCredit if is credit means pass
                if(!Objects.equals(studentAddMathGrade, "None") && !Objects.equals(studentAddMathGrade, "G"))
                {
                    dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                }
                else
                {
                    //check Science/Technical/Vocational subject got pass or not
                    if(Objects.equals(studentScienceTechnicalVocationalSubject, "Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Sains Pertanian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Ekonomi Rumah Tangga")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Lukisan Kejuruteraan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Mekanikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Awam")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Elektrik dan Elektronik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Reka Cipta")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Teknologi Kejuruteraan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Grafik Komunikasi Teknikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pembinaan Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Membuat Perabot")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Kerja Paip Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pendawaian Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Kimpalan Arka dan Gas")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Automobil")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Motosikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Peralatan Penyejukan dan Penyamanan Udara")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Peralatan Elektrik Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Rekaan dan Jahitan Pakaian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Katering dan Penyajian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pemprosesan Makanan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Penjagaan Muka dan Dandanan Rambut")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Asuhan dan Pendidikan Awal Kanak-Kanak")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Gerontologi Asas dan Perkhidmatan Geriatrik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Landskap dan Nurseri")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Akuakultur dan Haiwan Rekreasi")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Tanaman Makanan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Seni Reka Tanda")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Hiasan Dalaman Asas")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Produksi Multimedia")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Grafik Berkomputer"))
                    {
                        if(!Objects.equals(studentScienceTechnicalVocationalGrade, "G"))
                        {
                            dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                        }
                    }
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    dmeRuleAttribute.setGotEnglishSubjectAndPass();
                }

                if( !Objects.equals(studentMathematicsGrade, "U"))
                {
                    dmeRuleAttribute.setGotMathSubjectAndPass();
                }

                // Here scienceTechnicalVocationalSubjectsCredit if is credit means pass
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                }
                else
                {
                    //check Science/Technical/Vocational subject got pass or not
                    if(Objects.equals(studentScienceTechnicalVocationalSubject, "Agriculture")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Environmental Management")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Food and Nutrition")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Marine Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Science - Combined")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physical Education")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Sciences - Co-ordinated (Double)"))
                    {
                        if(!Objects.equals(studentScienceTechnicalVocationalGrade, "U"))
                        {
                            dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                        }
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    dmeRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check math and eng got pass or not
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    dmeRuleAttribute.setGotEnglishSubjectAndPass();
                }

                if(!Objects.equals(studentMathematicsGrade, "G"))
                {
                    // gotMathSubjectAndPass = true;
                    dmeRuleAttribute.setGotMathSubjectAndPass();
                }

                // Here scienceTechnicalVocationalSubjectsCredit if is credit means pass
                if(!Objects.equals(studentAddMathGrade, "None") && !Objects.equals(studentAddMathGrade, "G"))
                {
                    dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();

                }
                else
                {
                    //check Science/Technical/Vocational subject got pass or not
                    if(Objects.equals(studentScienceTechnicalVocationalSubject, "Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Sains Pertanian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Ekonomi Rumah Tangga")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Lukisan Kejuruteraan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Mekanikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Awam")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pengajian Kejuruteraan Elektrik dan Elektronik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Reka Cipta")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Teknologi Kejuruteraan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Grafik Komunikasi Teknikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pembinaan Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Membuat Perabot")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Kerja Paip Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pendawaian Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Kimpalan Arka dan Gas")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Automobil")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Motosikal")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Peralatan Penyejukan dan Penyamanan Udara")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Menservis Peralatan Elektrik Domestik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Rekaan dan Jahitan Pakaian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Katering dan Penyajian")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Pemprosesan Makanan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Penjagaan Muka dan Dandanan Rambut")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Asuhan dan Pendidikan Awal Kanak-Kanak")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Gerontologi Asas dan Perkhidmatan Geriatrik")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Landskap dan Nurseri")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Akuakultur dan Haiwan Rekreasi")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Tanaman Makanan")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Seni Reka Tanda")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Hiasan Dalaman Asas")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Produksi Multimedia")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Grafik Berkomputer"))
                    {
                        if(!Objects.equals(studentScienceTechnicalVocationalGrade, "G"))
                        {
                            dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                        }
                    }
                }
            }
            else if(Objects.equals(studentSPMOLevel, "O-Level"))
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    dmeRuleAttribute.setGotEnglishSubjectAndPass();
                }

                if( !Objects.equals(studentMathematicsGrade, "U"))
                {
                    dmeRuleAttribute.setGotMathSubjectAndPass();
                }

                // Here scienceTechnicalVocationalSubjectsCredit if is credit means pass
                if(!Objects.equals(studentAddMathGrade, "None") && !Objects.equals(studentAddMathGrade, "U"))
                {
                    dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                }
                else
                {
                    //check Science/Technical/Vocational subject got pass or not
                    if(Objects.equals(studentScienceTechnicalVocationalSubject, "Agriculture")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Biology")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Chemistry")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Environmental Management")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Food and Nutrition")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Marine Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Science - Combined")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physical Education")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Physics Science")
                            || Objects.equals(studentScienceTechnicalVocationalSubject, "Sciences - Co-ordinated (Double)"))
                    {
                        if(!Objects.equals(studentScienceTechnicalVocationalGrade, "U"))
                        {
                            dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
                        }
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dmeRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        dmeRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        dmeRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        dmeRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }

                // Check Science/Technical/Vocational subject got at least B or not
                if(Objects.equals(studentSubjects[i], "Digital Logic")
                        || Objects.equals(studentSubjects[i], "Basic Circuit Theory")
                        || Objects.equals(studentSubjects[i], "Principal Electronic")
                        || Objects.equals(studentSubjects[i], "Fundamentals of Electrical Engineering")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Industrial English")
                        || Objects.equals(studentSubjects[i], "Car Repairing")
                        || Objects.equals(studentSubjects[i], "Industrial Arts"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        dmeRuleAttribute.setScienceTechnicalVocationalSubjectsCredit();
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
                    dmeRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3, foundation, matriculation
        }

        // if enough credit, check english pass, science/technical/vocational credit
        // math subject pass or credit or not.
        // if all requirements satisfy, return true
        if(dmeRuleAttribute.getCountUEC() >= 3
                || dmeRuleAttribute.getCountSPM() >= 3
                || dmeRuleAttribute.getCountOLevel() >= 3
                || dmeRuleAttribute.getCountSTPM() >= 1
                || dmeRuleAttribute.getCountALevel() >= 1)
        {
            if(dmeRuleAttribute.isGotEnglishSubjectAndPass()
                    && (dmeRuleAttribute.isGotMathSubjectAndCredit() || dmeRuleAttribute.isGotMathSubjectAndPass())
                    && dmeRuleAttribute.isScienceTechnicalVocationalSubjectsCredit())
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
        dmeRuleAttribute.setJoinProgramme(true);
        Log.d("DiplMechaEngineering", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dmeRuleAttribute.isJoinProgramme();
    }
}
