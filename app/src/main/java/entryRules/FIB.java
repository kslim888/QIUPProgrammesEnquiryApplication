package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Arrays;

@Rule(name = "FIB/FIA", description = "Entry rule to join Foundation in Business or Foundation in Arts")
public class FIB
{
    RuleAttribute fibRuleAttribute = new RuleAttribute();

    public FIB()
    {
        fibRuleAttribute.setResultsTypeList(Arrays.asList("SPM", "O-Level", "UEC"));
        fibRuleAttribute.setNumberOfCredit(Arrays.asList(5, 3));
    }

    // when
    // receive parameter for how many credit, which results type
    // dont check for programme name. because wan see all programme qualify or not
    @Condition
    public boolean allowToJoin(@Fact("Number of Credit") int number, @Fact("Results Type") String resultsType)
    {
        if(resultsType.matches(fibRuleAttribute.getResultsTypeList().get(2))) // if is uec
        {
            if(number >= fibRuleAttribute.getNumberOfCredit().get(1)) // minimum 3 credit
                return true;
        }
        else // for SPM and O-Level
        {
            if(number >= fibRuleAttribute.getNumberOfCredit().get(0))  // minimum 5 credit
                return true;
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        fibRuleAttribute.setJoinProgramme(true);
        Log.d("joinProgramme : ", "Joined");
    }

    public boolean isJoinProgramme()
    {
        return fibRuleAttribute.isJoinProgramme();
    }

}
