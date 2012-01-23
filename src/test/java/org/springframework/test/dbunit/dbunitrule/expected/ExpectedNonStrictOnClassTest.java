/*
 * Copyright 2010 the original author or authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.test.dbunit.dbunitrule.expected;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.dbunit.DbUnitRule;
import org.springframework.test.dbunit.annotation.ExpectedDatabase;
import org.springframework.test.dbunit.entity.EntityAssert;
import org.springframework.test.dbunit.testutils.MustFailDbUnitRule;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/dbunit-context.xml")
@ExpectedDatabase("/META-INF/db/expectedfail.xml")
@Transactional
public class ExpectedNonStrictOnClassTest {

	@Rule
	public DbUnitRule dbUnit = new MustFailDbUnitRule();

	@Autowired
	private EntityAssert entityAssert;

	@Test
	public void test_nonstrict_does_not_throw_dbunit_exception_though_expected_table_does_not_specify_all_columns()
			throws Exception {
		this.entityAssert.assertValues("existing1", "existing2");
	}
}
