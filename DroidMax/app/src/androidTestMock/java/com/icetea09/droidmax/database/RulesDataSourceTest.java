package com.icetea09.droidmax.database;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.battery.BatteryRule;
import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class RulesDataSourceTest extends AndroidTestCase {

    private RulesDataSource mRulesDS;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mRulesDS = new RulesDataSource(context);
    }

    public void testAddNewRule() {
        Rule rule = createDummyRule();
        assertTrue(mRulesDS.addNewRule(rule));
        assertNotNull(mRulesDS.getRuleById(rule.getId()));
    }

    public void testGetRuleById() {
        Rule rule = createDummyRule();
        assertTrue(mRulesDS.addNewRule(rule));
        Rule savedRule = mRulesDS.getRuleById(rule.getId());
        assertNotNull(savedRule);
        assertEquals(rule.getId(), savedRule.getId());
        assertEquals(rule.getName(), savedRule.getName());
        assertEquals(rule.getCategoriesString(), savedRule.getCategoriesString());
        assertEquals(rule.getConditionsString(), savedRule.getConditionsString());
        assertEquals(rule.getConditions().size(), savedRule.getConditions().size());
        assertEquals(rule.getActionsString(), savedRule.getActionsString());
        assertEquals(rule.getActions().size(), savedRule.getActions().size());
        assertEquals(rule.getNumOfPerformed(), savedRule.getNumOfPerformed());
    }

    public void testUpdateRule() {
        Rule rule = createDummyRule();
        assertTrue(mRulesDS.addNewRule(rule));
        Rule savedRule = mRulesDS.getRuleById(rule.getId());
        assertNotNull(savedRule);
        savedRule.setNumOfPerformed(15);
        savedRule.setName("Updated name");
        assertTrue(mRulesDS.updateRule(savedRule));
        savedRule = mRulesDS.getRuleById(rule.getId());
        assertNotNull(savedRule);
        assertEquals("Updated name", savedRule.getName());
        assertEquals(15, savedRule.getNumOfPerformed());
    }

    public void testGetRulesByCategory() {
        List<Rule> rules = createDummyRules();
        for (Rule rule : rules) {
            assertTrue(mRulesDS.addNewRule(rule));
        }
        List<Rule> rulesByCategory = mRulesDS.getRulesByCategory(BatteryRule.TAG);
        assertEquals(4, rulesByCategory.size());
    }

    private Rule createDummyRule() {
        return createDummyRule(1, BatteryRule.TAG);
    }

    private Rule createDummyRule(int id, String category) {
        String strConditions = new ChargerPluggedRule().convertToString() + Rule.ITEMS_SEPARATOR + new LowBatteryRule("15").convertToString();
        Rule rule = new Rule();
        rule.setId(id);
        rule.setName("Test Rule");
        rule.setCategories(category);
        rule.setConditions(strConditions);
        rule.setActions("");
        rule.setNumOfPerformed(0);
        return rule;
    }

    private List<Rule> createDummyRules() {
        List<Rule> rules = new ArrayList<>();

        rules.add(createDummyRule());
        rules.add(createDummyRule(2, BatteryRule.TAG + "#" + "WifiRule"));
        rules.add(createDummyRule(3, "WifiRule#BluetoothRule"));
        rules.add(createDummyRule(4, "BluetoothRule" + "#" + BatteryRule.TAG));
        rules.add(createDummyRule(5, BatteryRule.TAG + "#" + "BluetoothRule"));

        return rules;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mRulesDS.deleteAllRules();
    }
}
