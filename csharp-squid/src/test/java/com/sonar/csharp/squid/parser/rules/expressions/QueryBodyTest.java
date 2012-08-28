/*
 * Copyright (C) 2009-2012 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.squid.parser.rules.expressions;

import static com.sonar.sslr.test.parser.ParserMatchers.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.squid.CSharpConfiguration;
import com.sonar.csharp.squid.api.CSharpGrammar;
import com.sonar.csharp.squid.parser.CSharpParser;
import com.sonar.sslr.impl.Parser;

public class QueryBodyTest {

  private final Parser<CSharpGrammar> p = CSharpParser.create(new CSharpConfiguration(Charset.forName("UTF-8")));
  private final CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.queryBody);
    g.queryBodyClause.mock();
    g.selectOrGroupClause.mock();
    g.queryContinuation.mock();
  }

  @Test
  public void testOk() {
    assertThat(p, parse("selectOrGroupClause"));
    assertThat(p, parse("selectOrGroupClause queryContinuation"));
    assertThat(p, parse("queryBodyClause selectOrGroupClause queryContinuation"));
    assertThat(p, parse("queryBodyClause queryBodyClause selectOrGroupClause queryContinuation"));
    assertThat(p, parse("queryBodyClause queryBodyClause selectOrGroupClause"));
  }

  @Test
  public void testKo() {
    assertThat(p, notParse(""));
  }

}