/**
 * [The "BSD license"]
 * Copyright (c) 2016, JGen Notes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *    and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.jgen.notes.dmw.lite.tests.inwork;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.tests.LangInjectorProvider;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(LangInjectorProvider.class)
@SuppressWarnings("all")
public class LangParsingTest2 {
  @Inject
  @Extension
  private ParseHelper<YWidget> parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Object {");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("public  clone() -> Object {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return this;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("  ");
      _builder.append("public  toString() -> String {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("// fake implementation");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return \"not implemented\";");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("  ");
      _builder.append("public  equals(Object o) -> Boolean {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("// fake implementation");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Text of unlimited length.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class String : Object {");
      _builder.newLine();
      _builder.append("   ");
      _builder.newLine();
      _builder.append("} ");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Int : Object {");
      _builder.newLine();
      _builder.append(" \t ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Short : Object {");
      _builder.newLine();
      _builder.append("   ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Double : Object {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Boolean : Object {");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Date : Object {");
      _builder.newLine();
      _builder.append("}  ");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Time : Object {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Timestamp : Object {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Widget : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  setExitState(ExistState exitState) -> String {");
      _builder.newLine();
      _builder.append("\t \t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  setCommand(Command cmd) -> String {");
      _builder.newLine();
      _builder.append("\t \t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  isExitState(ExistState exitState) -> Boolean {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  isCommand(Command cmd) -> Boolean {");
      _builder.newLine();
      _builder.append("\t \t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  moveView(View viewFrom, View viewTo) -> String {");
      _builder.newLine();
      _builder.append("\t \t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class ExistState : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Command : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class View : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Group : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  setSubscript(Int value)  {");
      _builder.newLine();
      _builder.append("\t  ");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  getSubscript() -> Int {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  getMax() -> Int {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("public  getLast() -> Int {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Relationship : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class RelOneToMany : Relationship {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append(" ");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class RelOneToOne : Relationship {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Identifier : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*  Entity represents a fundamental thing of relevance to the");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*  enterprise about which data may be kept.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/ ");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("class Entity : Object {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Exits   {  ");
      _builder.newLine();
      _builder.append("\t\t\t \t");
      _builder.append("public var  ExistState person_nf;");
      _builder.newLine();
      _builder.append("\t\t\t ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("class Person : Entity {     ");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("attr Int number #(9);");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("attr String firstName #(25);");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("attr String lastName #(25);");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("attr Date dateOfBirth;\t");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("id Identifier myid (number);          ");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t  ");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("class CreatePerson : Widget {");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("class PersonView : View -> Person {");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("public var  Int number;");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("public var  String firstName ;");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t\t ");
      _builder.append("var Exits exits;    ");
      _builder.newLine();
      _builder.append("\t\t\t\t ");
      _builder.append("var PersonView a ; ");
      _builder.newLine();
      _builder.append("\t\t\t\t ");
      _builder.append("var PersonView b ; ");
      _builder.newLine();
      _builder.append("\t\t\t\t ");
      _builder.append("var Group impGrp ;");
      _builder.newLine();
      _builder.append("\t\t\t\t\t ");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("public  hello() -> String { ");
      _builder.newLine();
      _builder.append("\t\t\t\t \t");
      _builder.append("super.moveView(this.a, this.b);    ");
      _builder.newLine();
      _builder.append("\t\t\t\t \t ");
      _builder.append("String name = \"hello\"; ");
      _builder.newLine();
      _builder.append("\t\t\t\t \t ");
      _builder.append("name = \"another hello\";");
      _builder.newLine();
      _builder.append("\t\t\t\t\t ");
      _builder.append("this.impGrp.setSubscript(1); ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("this.a.number = 1 + 2  + this.a.number; ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("if(this.a.number == 1) {");
      _builder.newLine();
      _builder.append("\t\t\t \t\t \t");
      _builder.append("this.moveView(this.a, this.b);");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("super.setExitState(this.exits.person_nf);");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("Boolean a = super.isExitState(this.exits.person_nf);  ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("if(super.isExitState(this.exits.person_nf)) {       ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t \t");
      _builder.append("if(true) {   ");
      _builder.newLine();
      _builder.append("\t\t\t \t\t \t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t \t\t ");
      _builder.newLine();
      _builder.append("\t\t\t\t\t ");
      _builder.append("return  this.a.firstName; ");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final YWidget result = this.parseHelper.parse(text);
      final Consumer<Issue> _function = (Issue it) -> {
        InputOutput.<Issue>println(it);
      };
      this._validationTestHelper.validate(result).forEach(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
