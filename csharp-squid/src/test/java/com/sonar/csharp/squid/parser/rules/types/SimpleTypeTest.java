/*
 * Copyright (C) 2009-2012 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.squid.parser.rules.types;

import static com.sonar.sslr.test.parser.ParserMatchers.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.squid.CSharpConfiguration;
import com.sonar.csharp.squid.api.CSharpGrammar;
import com.sonar.csharp.squid.parser.CSharpParser;
import com.sonar.sslr.impl.Parser;

public class SimpleTypeTest {

  private final Parser<CSharpGrammar> p = CSharpParser.create(new CSharpConfiguration(Charset.forName("UTF-8")));
  private final CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.simpleType);
  }

  @Test
  public void testOk() {
    assertThat(p, parse("bool"));
    assertThat(p, parse("decimal"));
    assertThat(p, parse("sbyte"));
    assertThat(p, parse("short"));
    assertThat(p, parse("ushort"));
    assertThat(p, parse("int"));
    assertThat(p, parse("uint"));
    assertThat(p, parse("long"));
    assertThat(p, parse("ulong"));
    assertThat(p, parse("char"));
    assertThat(p, parse("float"));
    assertThat(p, parse("double"));
  }

  @Test
  public void testKo() {
    assertThat(p, notParse("Float"));
    assertThat(p, notParse("string"));
    assertThat(p, notParse("object"));
  }

}