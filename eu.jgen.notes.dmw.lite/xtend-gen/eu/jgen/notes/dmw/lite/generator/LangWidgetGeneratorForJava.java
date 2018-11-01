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
package eu.jgen.notes.dmw.lite.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.LangFactory;
import eu.jgen.notes.dmw.lite.lang.YAccessLevel;
import eu.jgen.notes.dmw.lite.lang.YAndExpression;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotAction;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike;
import eu.jgen.notes.dmw.lite.lang.YAnnotDatabase;
import eu.jgen.notes.dmw.lite.lang.YAnnotDecimal;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefault;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultNumber;
import eu.jgen.notes.dmw.lite.lang.YAnnotDefaultText;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotId;
import eu.jgen.notes.dmw.lite.lang.YAnnotJava;
import eu.jgen.notes.dmw.lite.lang.YAnnotLength;
import eu.jgen.notes.dmw.lite.lang.YAnnotMax;
import eu.jgen.notes.dmw.lite.lang.YAnnotMessage;
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YAnnotSwift;
import eu.jgen.notes.dmw.lite.lang.YAnnotTable;
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign;
import eu.jgen.notes.dmw.lite.lang.YAnnotTop;
import eu.jgen.notes.dmw.lite.lang.YArgument;
import eu.jgen.notes.dmw.lite.lang.YAssignment;
import eu.jgen.notes.dmw.lite.lang.YAssociateStatement;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YBoolConstant;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YComparisonExpression;
import eu.jgen.notes.dmw.lite.lang.YCreateStatement;
import eu.jgen.notes.dmw.lite.lang.YDeleteStatement;
import eu.jgen.notes.dmw.lite.lang.YDisassociateStatement;
import eu.jgen.notes.dmw.lite.lang.YEqualityExpression;
import eu.jgen.notes.dmw.lite.lang.YExpression;
import eu.jgen.notes.dmw.lite.lang.YForInStatement;
import eu.jgen.notes.dmw.lite.lang.YFunction;
import eu.jgen.notes.dmw.lite.lang.YIfStatement;
import eu.jgen.notes.dmw.lite.lang.YImport;
import eu.jgen.notes.dmw.lite.lang.YIntConstant;
import eu.jgen.notes.dmw.lite.lang.YJoin;
import eu.jgen.notes.dmw.lite.lang.YJoinDef;
import eu.jgen.notes.dmw.lite.lang.YMember;
import eu.jgen.notes.dmw.lite.lang.YMemberSelection;
import eu.jgen.notes.dmw.lite.lang.YMinus;
import eu.jgen.notes.dmw.lite.lang.YMulOrDiv;
import eu.jgen.notes.dmw.lite.lang.YNamedElement;
import eu.jgen.notes.dmw.lite.lang.YNew;
import eu.jgen.notes.dmw.lite.lang.YNot;
import eu.jgen.notes.dmw.lite.lang.YNull;
import eu.jgen.notes.dmw.lite.lang.YOrExpression;
import eu.jgen.notes.dmw.lite.lang.YParameter;
import eu.jgen.notes.dmw.lite.lang.YParenties;
import eu.jgen.notes.dmw.lite.lang.YPlus;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement;
import eu.jgen.notes.dmw.lite.lang.YReadStatement;
import eu.jgen.notes.dmw.lite.lang.YRepeatWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YReturn;
import eu.jgen.notes.dmw.lite.lang.YSelf;
import eu.jgen.notes.dmw.lite.lang.YStatement;
import eu.jgen.notes.dmw.lite.lang.YStringConstant;
import eu.jgen.notes.dmw.lite.lang.YStructRefPair;
import eu.jgen.notes.dmw.lite.lang.YSwitchCase;
import eu.jgen.notes.dmw.lite.lang.YSwitchStatement;
import eu.jgen.notes.dmw.lite.lang.YSymbol;
import eu.jgen.notes.dmw.lite.lang.YSymbolRef;
import eu.jgen.notes.dmw.lite.lang.YTuples;
import eu.jgen.notes.dmw.lite.lang.YUpdateStatement;
import eu.jgen.notes.dmw.lite.lang.YVariableDeclaration;
import eu.jgen.notes.dmw.lite.lang.YWhere;
import eu.jgen.notes.dmw.lite.lang.YWhileStatement;
import eu.jgen.notes.dmw.lite.lang.YWidget;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangGeneratorHelperForJava;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.generator.trace.ILocationData;
import org.eclipse.xtext.generator.trace.node.CompositeGeneratorNode;
import org.eclipse.xtext.generator.trace.node.IGeneratorNode;
import org.eclipse.xtext.generator.trace.node.Traced;
import org.eclipse.xtext.generator.trace.node.TracedAccessors;
import org.eclipse.xtext.generator.trace.node.TracingSugar;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LangWidgetGeneratorForJava extends AbstractGenerator {
  @TracedAccessors(LangFactory.class)
  public static class LangTraceExtensions extends TracingSugar {
    public IGeneratorNode _left(final YAndExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _right(final YAndExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnot target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnot target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnot target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnot target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAbstractColumn target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAbstractColumn target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAbstractColumn target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotAbstractColumn target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _action(final YAnnotAction target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("action");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getAction());
      return trace;
    }
    
    public IGeneratorNode _action(final YAnnotAction target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("action");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getAction());
      return trace;
    }
    
    public IGeneratorNode _action(final YAnnotAction target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("action");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAction()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotAction target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotAction target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotAction target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotAction target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAttr target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAttr target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotAttr target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotAttr target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotAttr target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotAttr target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getOptional()));
      return trace;
    }
    
    public IGeneratorNode _yclass(final YAnnotAttr target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("yclass");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getYclass()));
      return trace;
    }
    
    public IGeneratorNode _attrref(final YAnnotColumn target, final Function<YAnnotAttr, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("attrref");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAttrref()));
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotColumn target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotColumn target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotColumn target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getOptional()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotColumn target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getType());
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotColumn target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getType());
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotColumn target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _columnref(final YAnnotColumnLike target, final Function<YAnnotAbstractColumn, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("columnref");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getColumnref()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotDatabase target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotDatabase target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotDatabase target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _decimal(final YAnnotDecimal target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("decimal");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getDecimal());
      return trace;
    }
    
    public IGeneratorNode _decimal(final YAnnotDecimal target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("decimal");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getDecimal());
      return trace;
    }
    
    public IGeneratorNode _decimal(final YAnnotDecimal target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("decimal");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getDecimal()));
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotDecimal target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotDecimal target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotDecimal target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLength()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDecimal target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDecimal target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDecimal target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotDecimal target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDefault target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDefault target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotDefault target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotDefault target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultNumber target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultNumber target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultNumber target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultText target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultText target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YAnnotDefaultText target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotEntity target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotEntity target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotEntity target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _superannot(final YAnnotEntity target, final Function<YAnnotEntity, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("superannot");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuperannot()));
      return trace;
    }
    
    public IGeneratorNode _relationship(final YAnnotForeignKey target, final Function<YAnnotRel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("relationship");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRelationship()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotId target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotId target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotId target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _database(final YAnnotJava target, final Function<YAnnotDatabase, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("database");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getDatabase()));
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotLength target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotLength target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotLength target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLength()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotLength target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotLength target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotLength target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotLength target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotMax target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotMax target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getLength());
      return trace;
    }
    
    public IGeneratorNode _length(final YAnnotMax target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("length");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLength()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMax target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMax target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMax target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotMax target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _msg(final YAnnotMessage target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msg");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsg());
      return trace;
    }
    
    public IGeneratorNode _msg(final YAnnotMessage target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msg");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsg());
      return trace;
    }
    
    public IGeneratorNode _msg(final YAnnotMessage target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msg");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsg()));
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMessage target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMessage target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getMsgtype());
      return trace;
    }
    
    public IGeneratorNode _msgtype(final YAnnotMessage target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("msgtype");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMsgtype()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotMessage target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _inverse(final YAnnotRel target, final Function<YAnnotRel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("inverse");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getInverse()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotRel target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotRel target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotRel target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _target(final YAnnotRel target, final Function<YAnnotEntity, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("target");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getTarget()));
      return trace;
    }
    
    public IGeneratorNode _many(final YAnnotRel target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("many");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isMany());
      return trace;
    }
    
    public IGeneratorNode _many(final YAnnotRel target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("many");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isMany());
      return trace;
    }
    
    public IGeneratorNode _many(final YAnnotRel target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("many");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isMany()));
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotRel target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotRel target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YAnnotRel target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isOptional()));
      return trace;
    }
    
    public IGeneratorNode _parent(final YAnnotRel target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("parent");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isParent());
      return trace;
    }
    
    public IGeneratorNode _parent(final YAnnotRel target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("parent");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isParent());
      return trace;
    }
    
    public IGeneratorNode _parent(final YAnnotRel target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("parent");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isParent()));
      return trace;
    }
    
    public IGeneratorNode _database(final YAnnotSwift target, final Function<YAnnotDatabase, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("database");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getDatabase()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotSwift target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotSwift target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotSwift target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _entityref(final YAnnotTable target, final Function<YAnnotEntity, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("entityref");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getEntityref()));
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotTable target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotTable target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YAnnotTable target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _primarykey(final YAnnotTable target, final Function<YAnnotPrimaryKey, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("primarykey");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getPrimarykey()));
      return trace;
    }
    
    public IGeneratorNode _database(final YAnnotTechnicalDesign target, final Function<YAnnotDatabase, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("database");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getDatabase()));
      return trace;
    }
    
    public IGeneratorNode _type(final YAnnotTop target, final Function<EObject, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YArgument target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YArgument target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YArgument target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _value(final YArgument target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YArgument target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YArgument target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _left(final YAssignment target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _right(final YAssignment target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _joinref(final YAssociateStatement target, final Function<YJoinDef, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("joinref");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getJoinref()));
      return trace;
    }
    
    public IGeneratorNode _struct(final YAssociateStatement target, final Function<YStructRefPair, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("struct");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStruct()));
      return trace;
    }
    
    public IGeneratorNode _value(final YBoolConstant target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YBoolConstant target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YBoolConstant target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _entityRef(final YClass target, final Function<YAnnotEntity, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("entityRef");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getEntityRef()));
      return trace;
    }
    
    public IGeneratorNode _superclass(final YClass target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("superclass");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuperclass()));
      return trace;
    }
    
    public IGeneratorNode _name(final YClass target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YClass target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YClass target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _left(final YComparisonExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _op(final YComparisonExpression target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YComparisonExpression target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YComparisonExpression target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getOp()));
      return trace;
    }
    
    public IGeneratorNode _right(final YComparisonExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _alreadyExist(final YCreateStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("alreadyExist");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAlreadyExist()));
      return trace;
    }
    
    public IGeneratorNode _setBlock(final YCreateStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("setBlock");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSetBlock()));
      return trace;
    }
    
    public IGeneratorNode _struct(final YCreateStatement target, final Function<YStructRefPair, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("struct");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStruct()));
      return trace;
    }
    
    public IGeneratorNode _success(final YCreateStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("success");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuccess()));
      return trace;
    }
    
    public IGeneratorNode _struct(final YDeleteStatement target, final Function<YStructRefPair, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("struct");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStruct()));
      return trace;
    }
    
    public IGeneratorNode _joinref(final YDisassociateStatement target, final Function<YJoinDef, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("joinref");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getJoinref()));
      return trace;
    }
    
    public IGeneratorNode _struct(final YDisassociateStatement target, final Function<YStructRefPair, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("struct");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStruct()));
      return trace;
    }
    
    public IGeneratorNode _left(final YEqualityExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _op(final YEqualityExpression target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YEqualityExpression target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YEqualityExpression target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getOp()));
      return trace;
    }
    
    public IGeneratorNode _right(final YEqualityExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _body(final YForInStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("body");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getBody()));
      return trace;
    }
    
    public IGeneratorNode _collection(final YForInStatement target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("collection");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getCollection()));
      return trace;
    }
    
    public IGeneratorNode _item(final YForInStatement target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("item");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getItem()));
      return trace;
    }
    
    public IGeneratorNode _body(final YFunction target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("body");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getBody()));
      return trace;
    }
    
    public IGeneratorNode _returnvalue(final YFunction target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("returnvalue");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isReturnvalue());
      return trace;
    }
    
    public IGeneratorNode _returnvalue(final YFunction target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("returnvalue");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isReturnvalue());
      return trace;
    }
    
    public IGeneratorNode _returnvalue(final YFunction target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("returnvalue");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isReturnvalue()));
      return trace;
    }
    
    public IGeneratorNode _access(final YFunction target, final Function<YAccessLevel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("access");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAccess()));
      return trace;
    }
    
    public IGeneratorNode _type(final YFunction target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YFunction target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YFunction target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YFunction target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _elseBlock(final YIfStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("elseBlock");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getElseBlock()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YIfStatement target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _thenBlock(final YIfStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("thenBlock");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getThenBlock()));
      return trace;
    }
    
    public IGeneratorNode _importedNamespace(final YImport target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("importedNamespace");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getImportedNamespace());
      return trace;
    }
    
    public IGeneratorNode _importedNamespace(final YImport target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("importedNamespace");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getImportedNamespace());
      return trace;
    }
    
    public IGeneratorNode _importedNamespace(final YImport target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("importedNamespace");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getImportedNamespace()));
      return trace;
    }
    
    public IGeneratorNode _value(final YIntConstant target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YIntConstant target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YIntConstant target, final Function<Integer, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _fromView(final YJoinDef target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("fromView");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getFromView()));
      return trace;
    }
    
    public IGeneratorNode _relRef(final YJoinDef target, final Function<YAnnotRel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("relRef");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRelRef()));
      return trace;
    }
    
    public IGeneratorNode _toView(final YJoinDef target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("toView");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getToView()));
      return trace;
    }
    
    public IGeneratorNode _type(final YJoinDef target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YJoinDef target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YJoinDef target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YJoinDef target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _access(final YMember target, final Function<YAccessLevel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("access");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAccess()));
      return trace;
    }
    
    public IGeneratorNode _type(final YMember target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YMember target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YMember target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YMember target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _member(final YMemberSelection target, final Function<YMember, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("member");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getMember()));
      return trace;
    }
    
    public IGeneratorNode _receiver(final YMemberSelection target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("receiver");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getReceiver()));
      return trace;
    }
    
    public IGeneratorNode _functioninvocation(final YMemberSelection target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("functioninvocation");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isFunctioninvocation());
      return trace;
    }
    
    public IGeneratorNode _functioninvocation(final YMemberSelection target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("functioninvocation");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isFunctioninvocation());
      return trace;
    }
    
    public IGeneratorNode _functioninvocation(final YMemberSelection target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("functioninvocation");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isFunctioninvocation()));
      return trace;
    }
    
    public IGeneratorNode _left(final YMinus target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _right(final YMinus target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _left(final YMulOrDiv target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _op(final YMulOrDiv target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YMulOrDiv target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getOp());
      return trace;
    }
    
    public IGeneratorNode _op(final YMulOrDiv target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("op");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getOp()));
      return trace;
    }
    
    public IGeneratorNode _right(final YMulOrDiv target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _name(final YNamedElement target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YNamedElement target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YNamedElement target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _type(final YNew target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YNot target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _left(final YOrExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _right(final YOrExpression target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _type(final YParameter target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YParameter target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YParameter target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YParameter target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _a(final YParenties target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("a");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getA()));
      return trace;
    }
    
    public IGeneratorNode _left(final YPlus target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("left");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getLeft()));
      return trace;
    }
    
    public IGeneratorNode _right(final YPlus target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("right");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getRight()));
      return trace;
    }
    
    public IGeneratorNode _attrRef(final YProperty target, final Function<YAnnotAttr, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("attrRef");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAttrRef()));
      return trace;
    }
    
    public IGeneratorNode _tuples(final YProperty target, final Function<YTuples, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("tuples");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getTuples()));
      return trace;
    }
    
    public IGeneratorNode _optional(final YProperty target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.isOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YProperty target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.isOptional());
      return trace;
    }
    
    public IGeneratorNode _optional(final YProperty target, final Function<Boolean, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("optional");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.isOptional()));
      return trace;
    }
    
    public IGeneratorNode _access(final YProperty target, final Function<YAccessLevel, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("access");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getAccess()));
      return trace;
    }
    
    public IGeneratorNode _type(final YProperty target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YProperty target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YProperty target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YProperty target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _joinclause(final YReadEachStatement target, final Function<YJoin, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("joinclause");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getJoinclause()));
      return trace;
    }
    
    public IGeneratorNode _success(final YReadEachStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("success");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuccess()));
      return trace;
    }
    
    public IGeneratorNode _target(final YReadEachStatement target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("target");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getTarget()));
      return trace;
    }
    
    public IGeneratorNode _whereclause(final YReadEachStatement target, final Function<YWhere, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("whereclause");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getWhereclause()));
      return trace;
    }
    
    public IGeneratorNode _joinclause(final YReadStatement target, final Function<YJoin, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("joinclause");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getJoinclause()));
      return trace;
    }
    
    public IGeneratorNode _notfound(final YReadStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("notfound");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getNotfound()));
      return trace;
    }
    
    public IGeneratorNode _success(final YReadStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("success");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuccess()));
      return trace;
    }
    
    public IGeneratorNode _whereclause(final YReadStatement target, final Function<YWhere, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("whereclause");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getWhereclause()));
      return trace;
    }
    
    public IGeneratorNode _body(final YRepeatWhileStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("body");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getBody()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YRepeatWhileStatement target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YReturn target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _value(final YStringConstant target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YStringConstant target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getValue());
      return trace;
    }
    
    public IGeneratorNode _value(final YStringConstant target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("value");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getValue()));
      return trace;
    }
    
    public IGeneratorNode _structclass(final YStructRefPair target, final Function<YAnnotEntity, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("structclass");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStructclass()));
      return trace;
    }
    
    public IGeneratorNode _structproperty(final YStructRefPair target, final Function<YProperty, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("structproperty");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStructproperty()));
      return trace;
    }
    
    public IGeneratorNode _caseExpression(final YSwitchCase target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("caseExpression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getCaseExpression()));
      return trace;
    }
    
    public IGeneratorNode _then(final YSwitchCase target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("then");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getThen()));
      return trace;
    }
    
    public IGeneratorNode _default(final YSwitchStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("default");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getDefault()));
      return trace;
    }
    
    public IGeneratorNode _switchExpression(final YSwitchStatement target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("switchExpression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSwitchExpression()));
      return trace;
    }
    
    public IGeneratorNode _type(final YSymbol target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YSymbol target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YSymbol target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YSymbol target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _symbol(final YSymbolRef target, final Function<YSymbol, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("symbol");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSymbol()));
      return trace;
    }
    
    public IGeneratorNode _setBlock(final YUpdateStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("setBlock");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSetBlock()));
      return trace;
    }
    
    public IGeneratorNode _struct(final YUpdateStatement target, final Function<YStructRefPair, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("struct");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getStruct()));
      return trace;
    }
    
    public IGeneratorNode _success(final YUpdateStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("success");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getSuccess()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YVariableDeclaration target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _type(final YVariableDeclaration target, final Function<YClass, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("type");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getType()));
      return trace;
    }
    
    public IGeneratorNode _name(final YVariableDeclaration target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YVariableDeclaration target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YVariableDeclaration target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YWhere target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _body(final YWhileStatement target, final Function<YBlock, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("body");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getBody()));
      return trace;
    }
    
    public IGeneratorNode _expression(final YWhileStatement target, final Function<YExpression, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("expression");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getExpression()));
      return trace;
    }
    
    public IGeneratorNode _name(final YWidget target) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YWidget target, final boolean useForDebugging) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location, useForDebugging);
      this.append(trace, target.getName());
      return trace;
    }
    
    public IGeneratorNode _name(final YWidget target, final Function<String, String> stringProvider) {
      EStructuralFeature feature = target.eClass().getEStructuralFeature("name");
      ILocationData location = this.location(target, feature, -1);
      CompositeGeneratorNode trace = this.trace(location);
      this.append(trace, stringProvider.apply(target.getName()));
      return trace;
    }
  }
  
  @Inject
  @Extension
  private LangWidgetGeneratorForJava.LangTraceExtensions _langTraceExtensions;
  
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private LangGeneratorHelperForJava _langGeneratorHelperForJava;
  
  @Inject
  @Extension
  private LangTypeComputer _langTypeComputer;
  
  private List<String> imports = CollectionLiterals.<String>newArrayList();
  
  private List<IGeneratorNode> innerFunctions2 = CollectionLiterals.<IGeneratorNode>newArrayList();
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    EObject _head = IterableExtensions.<EObject>head(input.getContents());
    final YWidget model = ((YWidget) _head);
    final Function1<EObject, Boolean> _function = (EObject it) -> {
      return Boolean.valueOf((it instanceof YAnnotJava));
    };
    boolean _exists = IteratorExtensions.<EObject>exists(input.getAllContents(), _function);
    if (_exists) {
      final Function1<EObject, Boolean> _function_1 = (EObject it) -> {
        return Boolean.valueOf((it instanceof YWidget));
      };
      final Iterator<EObject> list = IteratorExtensions.<EObject>filter(input.getAllContents(), _function_1);
      final Procedure1<EObject> _function_2 = (EObject it) -> {
        final YWidget widget = ((YWidget) it);
        final Consumer<YClass> _function_3 = (YClass clazz) -> {
          if (((clazz.getSuperclass() != null) && Objects.equal(clazz.getSuperclass().getName(), "Widget"))) {
            this._langGeneratorHelperForJava.resetLocalNames();
            this.innerFunctions2.clear();
            String _fileSystemPath = this._langUtil.getFileSystemPath(widget.getName());
            String _plus = (_fileSystemPath + "/");
            String _name = clazz.getName();
            String _plus_1 = (_plus + _name);
            String _plus_2 = (_plus_1 + ".java");
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("/*");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("* Generated by Lang Widget Generator for Java, Version 0.4");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("*/\t");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("package ");
                String _name = widget.getName();
                _builder.append(_name, " ");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XWidget;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import eu.jgen.notes.dmw.lite.runtimes.DMWContext;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XStructure;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.Connection;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.SQLException;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.PreparedStatement;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.ResultSet;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.Date;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.Time;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.sql.Timestamp;\t");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import eu.jgen.notes.dmw.lite.runtimes.DMWRuntimeException;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import eu.jgen.notes.dmw.lite.runtimes.XArray;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.util.SortedMap;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import java.util.concurrent.ConcurrentSkipListMap;");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("import com.google.common.base.Objects;");
                _builder.newLine();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(clazz);
                _builder.append(_documentation, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.append("@SuppressWarnings(\"unused\")\t\t\t\t\t");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("public class ");
                String _name_1 = clazz.getName();
                _builder.append(_name_1, " ");
                _builder.append(" extends XWidget {\t");
                _builder.newLineIfNotEmpty();
                _builder.append(" \t");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateConstructor = LangWidgetGeneratorForJava.this.generateConstructor(clazz);
                _builder.append(_generateConstructor, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateGetInstance = LangWidgetGeneratorForJava.this.generateGetInstance(clazz);
                _builder.append(_generateGetInstance, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateInnerClasses = LangWidgetGeneratorForJava.this.generateInnerClasses(clazz);
                _builder.append(_generateInnerClasses, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateProperties = LangWidgetGeneratorForJava.this.generateProperties(clazz);
                _builder.append(_generateProperties, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateArrays = LangWidgetGeneratorForJava.this.generateArrays(clazz);
                _builder.append(_generateArrays, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                IGeneratorNode _generateFunctions = LangWidgetGeneratorForJava.this.generateFunctions(clazz);
                _builder.append(_generateFunctions, " ");
                _builder.newLineIfNotEmpty();
                _builder.append(" ");
                _builder.newLine();
                {
                  for(final IGeneratorNode function : LangWidgetGeneratorForJava.this.innerFunctions2) {
                    _builder.append(" ");
                    _builder.append(function, " ");
                    _builder.newLineIfNotEmpty();
                  }
                }
                _builder.append(" ");
                _builder.newLine();
                _builder.append(" ");
                _builder.append("}");
                _builder.newLine();
                _builder.append(" ");
                _builder.newLine();
              }
            };
            this._langTraceExtensions.generateTracedFile(fsa, _plus_2, model, _client);
          }
        };
        widget.getClasses().forEach(_function_3);
      };
      IteratorExtensions.<EObject>forEach(list, _function_2);
    }
  }
  
  /**
   * Generate get instance static method
   */
  @Traced
  protected IGeneratorNode generateGetInstance(final YClass it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateGetInstance(it));
    return _traceNode;
  }
  
  /**
   * Generate constructor for the widget class.
   */
  @Traced
  protected IGeneratorNode generateConstructor(final YClass it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateConstructor(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateInnerClasses(final YClass clazz) {
    ILocationData _location = this._langTraceExtensions.location(clazz);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateInnerClasses(clazz));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateClass(final YClass it, final String widgetName) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateClass(it,widgetName));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generatePropertyForStructure(final YProperty property) {
    ILocationData _location = this._langTraceExtensions.location(property);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generatePropertyForStructure(property));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateProperties(final YClass it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateProperties(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateProperty(final YProperty it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateProperty(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateFunctions(final YClass it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateFunctions(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateFunctionForWidget(final YFunction it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateFunctionForWidget(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateFunctionParameters(final YFunction it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateFunctionParameters(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateArrays(final YClass it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateArrays(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateBlock(final YBlock it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateBlock(it));
    return _traceNode;
  }
  
  /**
   * Select type of the statement and generate code.
   */
  protected Object selectStatementGeneration(final YStatement statement) {
    boolean _matched = false;
    if ((statement instanceof YCreateStatement)) {
      _matched=true;
      return this.generateStatementCreate(((YCreateStatement) statement));
    }
    if (!_matched) {
      if ((statement instanceof YDeleteStatement)) {
        _matched=true;
        return this.generateStatementDelete(((YDeleteStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YUpdateStatement)) {
        _matched=true;
        return this.generateStatementUpdate(((YUpdateStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReadStatement)) {
        _matched=true;
        return this.generateStatementRead(((YReadStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YAssociateStatement)) {
        _matched=true;
        return this.generateStatementAssociate(((YAssociateStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReadEachStatement)) {
        _matched=true;
        return this.generateStatementReadEach(((YReadEachStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YForInStatement)) {
        _matched=true;
        return this.generateStatementForIn(((YForInStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YIfStatement)) {
        _matched=true;
        return this.generateStatementIf(((YIfStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YReturn)) {
        _matched=true;
        return this.generateStatementReturn(((YReturn) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YWhileStatement)) {
        _matched=true;
        return this.generateStatementWhile(((YWhileStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YSwitchStatement)) {
        _matched=true;
        return this.generateStatementSwitch(((YSwitchStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YRepeatWhileStatement)) {
        _matched=true;
        return this.generateStatementRepeatWhile(((YRepeatWhileStatement) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YVariableDeclaration)) {
        _matched=true;
        return this.generateStatementVariableDeclaration(((YVariableDeclaration) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YAssignment)) {
        _matched=true;
        return this.generateStatementYAssignment(((YAssignment) statement));
      }
    }
    if (!_matched) {
      if ((statement instanceof YMemberSelection)) {
        _matched=true;
        return this.generateStatementYAssignment(((YMemberSelection) statement));
      }
    }
    return null;
  }
  
  @Traced
  protected IGeneratorNode generateStatementIf(final YIfStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementIf(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementForIn(final YForInStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementForIn(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementReturn(final YReturn it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementReturn(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementWhile(final YWhileStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementWhile(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementRepeatWhile(final YRepeatWhileStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementRepeatWhile(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementVariableDeclaration(final YVariableDeclaration it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementVariableDeclaration(it));
    return _traceNode;
  }
  
  private String generateVariableDeclaration(final YVariableDeclaration variableDeclaration) {
    String _translateTypeName = this._langGeneratorHelperForJava.translateTypeName(variableDeclaration.getType().getName());
    String _plus = (_translateTypeName + " ");
    String _name = variableDeclaration.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + " = ");
    String _generateExpression = this.generateExpression(variableDeclaration.getExpression());
    String _plus_3 = (_plus_2 + _generateExpression);
    return (_plus_3 + ";");
  }
  
  @Traced
  protected IGeneratorNode generateStatementYAssignment(final YMemberSelection it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementYAssignment(it));
    return _traceNode;
  }
  
  protected CompositeGeneratorNode generateStatementSwitch(final YSwitchStatement it) {
    final CompositeGeneratorNode buffer = this._langTraceExtensions.trace(it);
    String key = this._langGeneratorHelperForJava.generateLocalName("key");
    String _matched = this._langGeneratorHelperForJava.generateLocalName("_matched");
    this._langTraceExtensions.append(buffer, "final ");
    this._langTraceExtensions.append(buffer, this._langGeneratorHelperForJava.translateTypeName(this._langTypeComputer.typeFor(it.getSwitchExpression()).getName()));
    this._langTraceExtensions.append(buffer, " ");
    this._langTraceExtensions.append(buffer, key);
    this._langTraceExtensions.append(buffer, " = ");
    this._langTraceExtensions.append(buffer, this.generateExpression(it.getSwitchExpression()));
    this._langTraceExtensions.append(buffer, ";");
    this._langTraceExtensions.appendNewLine(buffer);
    this._langTraceExtensions.append(buffer, "boolean ");
    this._langTraceExtensions.append(buffer, _matched);
    this._langTraceExtensions.append(buffer, " = false;");
    this._langTraceExtensions.appendNewLine(buffer);
    boolean firstItem = true;
    EList<YSwitchCase> _cases = it.getCases();
    for (final YSwitchCase caseFragment : _cases) {
      if (firstItem) {
        this._langTraceExtensions.append(buffer, "if (Objects.equal(");
        this._langTraceExtensions.append(buffer, key);
        this._langTraceExtensions.append(buffer, ", ");
        this._langTraceExtensions.append(buffer, this.generateExpression(caseFragment.getCaseExpression()));
        this._langTraceExtensions.append(buffer, ")) {");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, _matched);
        this._langTraceExtensions.append(buffer, " = true;");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, this.generateBlock(caseFragment.getThen()));
        this._langTraceExtensions.append(buffer, "}");
        this._langTraceExtensions.appendNewLine(buffer);
        firstItem = false;
      } else {
        this._langTraceExtensions.append(buffer, "if (!");
        this._langTraceExtensions.append(buffer, _matched);
        this._langTraceExtensions.append(buffer, ") {");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, "if (Objects.equal(");
        this._langTraceExtensions.append(buffer, key);
        this._langTraceExtensions.append(buffer, ", ");
        this._langTraceExtensions.append(buffer, this.generateExpression(caseFragment.getCaseExpression()));
        this._langTraceExtensions.append(buffer, ")) {");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, _matched);
        this._langTraceExtensions.append(buffer, " = true;");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, this.generateBlock(caseFragment.getThen()));
        this._langTraceExtensions.append(buffer, "}");
        this._langTraceExtensions.appendNewLine(buffer);
        this._langTraceExtensions.append(buffer, "}");
        this._langTraceExtensions.appendNewLine(buffer);
      }
    }
    this._langTraceExtensions.append(buffer, "if (!");
    this._langTraceExtensions.append(buffer, _matched);
    this._langTraceExtensions.append(buffer, ") {");
    this._langTraceExtensions.appendNewLine(buffer);
    this._langTraceExtensions.append(buffer, this.generateBlock(it.getDefault()));
    this._langTraceExtensions.appendNewLine(buffer);
    this._langTraceExtensions.append(buffer, "}");
    this._langTraceExtensions.appendNewLine(buffer);
    return buffer;
  }
  
  @Traced
  protected IGeneratorNode generateStatementCreate(final YCreateStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementCreate(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementCreateFunction(final YCreateStatement it, final String name) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementCreateFunction(it,name));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementAssociate(final YAssociateStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementAssociate(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementAssociateFunction(final YAssociateStatement statement, final String name) {
    ILocationData _location = this._langTraceExtensions.location(statement);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementAssociateFunction(statement,name));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementDelete(final YDeleteStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementDelete(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementDeleteFunction(final YDeleteStatement it, final String functionName) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementDeleteFunction(it,functionName));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementUpdate(final YUpdateStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementUpdate(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementUpdateFunction(final YUpdateStatement it, final String functionName) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementUpdateFunction(it,functionName));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementReadEach(final YReadEachStatement it) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementReadEach(it));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementReadEachFunction(final YReadEachStatement it, final String functionName) {
    ILocationData _location = this._langTraceExtensions.location(it);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementReadEachFunction(it,functionName));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementRead(final YReadStatement readStatement) {
    ILocationData _location = this._langTraceExtensions.location(readStatement);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementRead(readStatement));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateStatementReadFunction(final YReadStatement readStatement, final String _read) {
    ILocationData _location = this._langTraceExtensions.location(readStatement);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateStatementReadFunction(readStatement,_read));
    return _traceNode;
  }
  
  /**
   * Generate content of the constructor for the widget class. It is a sequence
   * of methods initialising structures and setting default values for each property.
   */
  @Traced
  protected IGeneratorNode generateInitializeStructure(final YClass clazz, final YProperty property) {
    ILocationData _location = this._langTraceExtensions.location(clazz);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateInitializeStructure(clazz,property));
    return _traceNode;
  }
  
  @Traced
  protected IGeneratorNode generateInitStructureFunction(final YProperty property, final String methodName) {
    ILocationData _location = this._langTraceExtensions.location(property);
    CompositeGeneratorNode _traceNode = this._langTraceExtensions.trace(_location, false);
    this._langTraceExtensions.appendTemplate(_traceNode, _generateInitStructureFunction(property,methodName));
    return _traceNode;
  }
  
  private String generateStatementYAssignment(final YAssignment assignment) {
    StringConcatenation _builder = new StringConcatenation();
    String _documentation = this._langGeneratorHelperForJava.getDocumentation(assignment);
    _builder.append(_documentation);
    _builder.newLineIfNotEmpty();
    String _generateAssigment = this.generateAssigment(assignment);
    _builder.append(_generateAssigment);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private String generateJoinExpressionForRead(final YReadStatement statement) {
    YJoin _joinclause = statement.getJoinclause();
    boolean _tripleEquals = (_joinclause == null);
    if (_tripleEquals) {
      return "";
    }
    EList<YJoinDef> _joindefs = statement.getJoinclause().getJoindefs();
    for (final YJoinDef joinDef : _joindefs) {
      return this.generateJoinExpressionFragment(statement, joinDef);
    }
    return "something wrong";
  }
  
  private String generateJoinExpressionForReadEach(final YReadEachStatement readEachStatement) {
    YJoin _joinclause = readEachStatement.getJoinclause();
    boolean _tripleEquals = (_joinclause == null);
    if (_tripleEquals) {
      return "";
    }
    EList<YJoinDef> _joindefs = readEachStatement.getJoinclause().getJoindefs();
    for (final YJoinDef joinDef : _joindefs) {
      return this.generateJoinExpressionFragmentForReadEach(readEachStatement, joinDef);
    }
    return "something wrong";
  }
  
  private String generateJoinExpressionFragment(final YReadStatement readStatement, final YJoinDef joinDef) {
    final YAnnotTable fromTable = this._langUtil.getImplementingTable(joinDef.getFromView().getType().getEntityRef());
    final String fromPrefix = this.findTablePrefix(readStatement.getStructs(), joinDef.getFromView());
    final YAnnotTable toTable = this._langUtil.getImplementingTable(joinDef.getToView().getType().getEntityRef());
    final String toPrefix = this.findTablePrefix(readStatement.getStructs(), joinDef.getToView());
    final boolean parent = joinDef.getRelRef().isParent();
    if (parent) {
      StringBuffer buffer = new StringBuffer();
      String columnName = "";
      String cadidateColumnName = null;
      EList<YAnnotAbstractColumn> _columns = fromTable.getPrimarykey().getColumns();
      for (final YAnnotAbstractColumn abstractElement : _columns) {
        {
          columnName = this.getAttributeNameForAbstractColumn(abstractElement);
          EList<YAnnotForeignKey> _foreignkeys = toTable.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            String _name = joinDef.getRelRef().getInverse().getName();
            String _name_1 = foreignKey.getRelationship().getName();
            boolean _equals = Objects.equal(_name, _name_1);
            if (_equals) {
              EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
              for (final YAnnotAbstractColumn cadidateElement : _columns_1) {
                {
                  cadidateColumnName = this.getAttributeNameForAbstractColumn(cadidateElement);
                  boolean _equals_1 = Objects.equal(columnName, cadidateColumnName);
                  if (_equals_1) {
                    String _name_2 = abstractElement.getName();
                    String _plus = ((fromPrefix + ".") + _name_2);
                    String _plus_1 = (_plus + " = ");
                    String _plus_2 = (_plus_1 + toPrefix);
                    String _plus_3 = (_plus_2 + ".");
                    String _name_3 = cadidateElement.getName();
                    String _plus_4 = (_plus_3 + _name_3);
                    String _plus_5 = (_plus_4 + " AND");
                    buffer.append(_plus_5);
                  }
                }
              }
            }
          }
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  private String generateJoinExpressionFragmentForReadEach(final YReadEachStatement readEachStatement, final YJoinDef joinDef) {
    final YAnnotTable fromTable = this._langUtil.getImplementingTable(joinDef.getFromView().getType().getEntityRef());
    final String fromPrefix = this.findTablePrefix(readEachStatement.getStructs(), joinDef.getFromView());
    final YAnnotTable toTable = this._langUtil.getImplementingTable(joinDef.getToView().getType().getEntityRef());
    final String toPrefix = this.findTablePrefix(readEachStatement.getStructs(), joinDef.getToView());
    final boolean parent = joinDef.getRelRef().isParent();
    if (parent) {
      StringBuffer buffer = new StringBuffer();
      String columnName = "";
      String cadidateColumnName = null;
      EList<YAnnotAbstractColumn> _columns = fromTable.getPrimarykey().getColumns();
      for (final YAnnotAbstractColumn abstractElement : _columns) {
        {
          columnName = this.getAttributeNameForAbstractColumn(abstractElement);
          EList<YAnnotForeignKey> _foreignkeys = toTable.getForeignkeys();
          for (final YAnnotForeignKey foreignKey : _foreignkeys) {
            String _name = joinDef.getRelRef().getInverse().getName();
            String _name_1 = foreignKey.getRelationship().getName();
            boolean _equals = Objects.equal(_name, _name_1);
            if (_equals) {
              EList<YAnnotAbstractColumn> _columns_1 = foreignKey.getColumns();
              for (final YAnnotAbstractColumn cadidateElement : _columns_1) {
                {
                  cadidateColumnName = this.getAttributeNameForAbstractColumn(cadidateElement);
                  boolean _equals_1 = Objects.equal(columnName, cadidateColumnName);
                  if (_equals_1) {
                    String _name_2 = abstractElement.getName();
                    String _plus = ((fromPrefix + ".") + _name_2);
                    String _plus_1 = (_plus + " = ");
                    String _plus_2 = (_plus_1 + toPrefix);
                    String _plus_3 = (_plus_2 + ".");
                    String _name_3 = cadidateElement.getName();
                    String _plus_4 = (_plus_3 + _name_3);
                    String _plus_5 = (_plus_4 + " AND");
                    buffer.append(_plus_5);
                  }
                }
              }
            }
          }
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  private String findTablePrefix(final EList<YStructRefPair> list, final YProperty property) {
    String _xblockexpression = null;
    {
      int index = 1;
      for (final YStructRefPair pair : list) {
        {
          YProperty _structproperty = pair.getStructproperty();
          boolean _equals = Objects.equal(_structproperty, property);
          if (_equals) {
            return ("T" + Integer.valueOf(index));
          }
          index++;
        }
      }
      _xblockexpression = "";
    }
    return _xblockexpression;
  }
  
  private String getAttributeNameForAbstractColumn(final YAnnotAbstractColumn annotAbstractColumn) {
    EObject _type = annotAbstractColumn.getType();
    if ((_type instanceof YAnnotColumnLike)) {
      EObject _type_1 = annotAbstractColumn.getType();
      EObject _type_2 = ((YAnnotColumnLike) _type_1).getColumnref().getType();
      return ((YAnnotColumn) _type_2).getAttrref().getName();
    } else {
      EObject _type_3 = annotAbstractColumn.getType();
      return ((YAnnotColumn) _type_3).getAttrref().getName();
    }
  }
  
  private ArrayList<String> generateFROMClause(final YStatement statement) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList();
    int index = 1;
    if ((statement instanceof YReadStatement)) {
      final YReadStatement readStatement = ((YReadStatement) statement);
      EList<YStructRefPair> _structs = readStatement.getStructs();
      for (final YStructRefPair struct : _structs) {
        {
          final YAnnotTable implementingTable = this._langUtil.getImplementingTable(readStatement.getStructs().get(0).getStructclass());
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("\\\"");
          String _name = implementingTable.getName();
          _builder.append(_name);
          _builder.append("\\\" T");
          _builder.append(index);
          list.add(_builder.toString());
          index++;
        }
      }
    }
    return list;
  }
  
  private String generateAssigment(final YAssignment assignment) {
    YExpression _left = assignment.getLeft();
    if ((_left instanceof YMemberSelection)) {
      YExpression _left_1 = assignment.getLeft();
      String _generateMemberSelection = this.generateMemberSelection(((YMemberSelection) _left_1));
      String _plus = (_generateMemberSelection + " = ");
      String _generateExpression = this.generateExpression(assignment.getRight());
      String _plus_1 = (_plus + _generateExpression);
      return (_plus_1 + ";");
    } else {
      YExpression _left_2 = assignment.getLeft();
      if ((_left_2 instanceof YSymbolRef)) {
        YExpression _left_3 = assignment.getLeft();
        final YSymbolRef symbolRef = ((YSymbolRef) _left_3);
        String _name = symbolRef.getSymbol().getName();
        String _plus_2 = (_name + " = ");
        String _generateExpression_1 = this.generateExpression(assignment.getRight());
        String _plus_3 = (_plus_2 + _generateExpression_1);
        return (_plus_3 + ";");
      }
      return "?";
    }
  }
  
  private String generateInitializeProperty(final YProperty property, final String structureName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(structureName);
    _builder.append(".");
    String _name = property.getName();
    _builder.append(_name);
    _builder.append(" = ");
    String _propertyDefaultValue = this._langGeneratorHelperForJava.getPropertyDefaultValue(property);
    _builder.append(_propertyDefaultValue);
    _builder.append(";");
    return _builder.toString();
  }
  
  private String registerImport(final String name) {
    boolean _contains = this.imports.contains(name);
    if (_contains) {
      return "";
    }
    this.imports.add(name);
    return "";
  }
  
  private String generateExpression(final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YPlus)) {
      _matched=true;
      final YPlus plus = ((YPlus) expression);
      String _generateExpression = this.generateExpression(plus.getLeft());
      String _plus = (_generateExpression + " + ");
      String _generateExpression_1 = this.generateExpression(plus.getRight());
      return (_plus + _generateExpression_1);
    }
    if (!_matched) {
      if ((expression instanceof YMinus)) {
        _matched=true;
        final YMinus minus = ((YMinus) expression);
        String _generateExpression_2 = this.generateExpression(minus.getLeft());
        String _plus_1 = (_generateExpression_2 + " - ");
        String _generateExpression_3 = this.generateExpression(minus.getRight());
        return (_plus_1 + _generateExpression_3);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMulOrDiv)) {
        _matched=true;
        final YMulOrDiv mulOrDiv = ((YMulOrDiv) expression);
        String _generateExpression_4 = this.generateExpression(mulOrDiv.getLeft());
        String _plus_2 = (_generateExpression_4 + " ");
        String _op = mulOrDiv.getOp();
        String _plus_3 = (_plus_2 + _op);
        String _plus_4 = (_plus_3 + " ");
        String _generateExpression_5 = this.generateExpression(mulOrDiv.getRight());
        return (_plus_4 + _generateExpression_5);
      }
    }
    if (!_matched) {
      if ((expression instanceof YAndExpression)) {
        _matched=true;
        final YAndExpression andExpression = ((YAndExpression) expression);
        String _generateExpression_6 = this.generateExpression(andExpression.getLeft());
        String _plus_5 = (_generateExpression_6 + " ");
        String _plus_6 = (_plus_5 + " && ");
        String _plus_7 = (_plus_6 + " ");
        String _generateExpression_7 = this.generateExpression(andExpression.getRight());
        return (_plus_7 + _generateExpression_7);
      }
    }
    if (!_matched) {
      if ((expression instanceof YOrExpression)) {
        _matched=true;
        final YOrExpression orExpression = ((YOrExpression) expression);
        String _generateExpression_8 = this.generateExpression(orExpression.getLeft());
        String _plus_8 = (_generateExpression_8 + " ");
        String _plus_9 = (_plus_8 + " || ");
        String _plus_10 = (_plus_9 + " ");
        String _generateExpression_9 = this.generateExpression(orExpression.getRight());
        return (_plus_10 + _generateExpression_9);
      }
    }
    if (!_matched) {
      if ((expression instanceof YComparisonExpression)) {
        _matched=true;
        final YComparisonExpression comparisonExpression = ((YComparisonExpression) expression);
        String _generateExpression_10 = this.generateExpression(comparisonExpression.getLeft());
        String _plus_11 = (_generateExpression_10 + " ");
        String _op_1 = comparisonExpression.getOp();
        String _plus_12 = (_plus_11 + _op_1);
        String _plus_13 = (_plus_12 + " ");
        String _generateExpression_11 = this.generateExpression(comparisonExpression.getRight());
        return (_plus_13 + _generateExpression_11);
      }
    }
    if (!_matched) {
      if ((expression instanceof YEqualityExpression)) {
        _matched=true;
        final YEqualityExpression equalityExpression = ((YEqualityExpression) expression);
        String _generateExpression_12 = this.generateExpression(equalityExpression.getLeft());
        String _plus_14 = (_generateExpression_12 + " ");
        String _op_2 = equalityExpression.getOp();
        String _plus_15 = (_plus_14 + _op_2);
        String _plus_16 = (_plus_15 + " ");
        String _generateExpression_13 = this.generateExpression(equalityExpression.getRight());
        return (_plus_16 + _generateExpression_13);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMemberSelection)) {
        _matched=true;
        final YMemberSelection memberSelection = ((YMemberSelection) expression);
        return this.generateMemberSelection(memberSelection);
      }
    }
    if (!_matched) {
      if ((expression instanceof YSelf)) {
        _matched=true;
        return "this";
      }
    }
    if (!_matched) {
      if ((expression instanceof YNull)) {
        _matched=true;
        return "null";
      }
    }
    if (!_matched) {
      if ((expression instanceof YNot)) {
        _matched=true;
        final YNot not = ((YNot) expression);
        String _generateExpression_14 = this.generateExpression(not.getExpression());
        return ("!" + _generateExpression_14);
      }
    }
    if (!_matched) {
      if ((expression instanceof YBoolConstant)) {
        _matched=true;
        final YBoolConstant boolConstant = ((YBoolConstant) expression);
        return boolConstant.getValue();
      }
    }
    if (!_matched) {
      if ((expression instanceof YParenties)) {
        _matched=true;
        String _generateExpression_15 = this.generateExpression(((YParenties) expression).getA());
        String _plus_17 = ("(" + _generateExpression_15);
        return (_plus_17 + ")");
      }
    }
    if (!_matched) {
      if ((expression instanceof YSymbolRef)) {
        _matched=true;
        final YSymbolRef symbolRef = ((YSymbolRef) expression);
        return symbolRef.getSymbol().getName();
      }
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus_18 = ("\"" + _string);
          _xblockexpression_1 = (_plus_18 + "\"");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  private String generateJDBCExpression(final YStatement statement, final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YPlus)) {
      _matched=true;
      final YPlus plus = ((YPlus) expression);
      String _generateJDBCExpression = this.generateJDBCExpression(statement, plus.getLeft());
      String _plus = (_generateJDBCExpression + " + ");
      String _generateJDBCExpression_1 = this.generateJDBCExpression(statement, plus.getRight());
      return (_plus + _generateJDBCExpression_1);
    }
    if (!_matched) {
      if ((expression instanceof YMinus)) {
        _matched=true;
        final YMinus minus = ((YMinus) expression);
        String _generateJDBCExpression_2 = this.generateJDBCExpression(statement, minus.getLeft());
        String _plus_1 = (_generateJDBCExpression_2 + " - ");
        String _generateJDBCExpression_3 = this.generateJDBCExpression(statement, minus.getRight());
        return (_plus_1 + _generateJDBCExpression_3);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMulOrDiv)) {
        _matched=true;
        final YMulOrDiv mulOrDiv = ((YMulOrDiv) expression);
        String _generateJDBCExpression_4 = this.generateJDBCExpression(statement, mulOrDiv.getLeft());
        String _plus_2 = (_generateJDBCExpression_4 + " ");
        String _op = mulOrDiv.getOp();
        String _plus_3 = (_plus_2 + _op);
        String _plus_4 = (_plus_3 + " ");
        String _generateJDBCExpression_5 = this.generateJDBCExpression(statement, mulOrDiv.getRight());
        return (_plus_4 + _generateJDBCExpression_5);
      }
    }
    if (!_matched) {
      if ((expression instanceof YAndExpression)) {
        _matched=true;
        final YAndExpression andExpression = ((YAndExpression) expression);
        String _generateJDBCExpression_6 = this.generateJDBCExpression(statement, andExpression.getLeft());
        String _plus_5 = (_generateJDBCExpression_6 + " ");
        String _plus_6 = (_plus_5 + " AND ");
        String _plus_7 = (_plus_6 + " ");
        String _generateJDBCExpression_7 = this.generateJDBCExpression(statement, andExpression.getRight());
        return (_plus_7 + _generateJDBCExpression_7);
      }
    }
    if (!_matched) {
      if ((expression instanceof YOrExpression)) {
        _matched=true;
        final YOrExpression orExpression = ((YOrExpression) expression);
        String _generateJDBCExpression_8 = this.generateJDBCExpression(statement, orExpression.getLeft());
        String _plus_8 = (_generateJDBCExpression_8 + " ");
        String _plus_9 = (_plus_8 + " OR ");
        String _plus_10 = (_plus_9 + " ");
        String _generateJDBCExpression_9 = this.generateJDBCExpression(statement, orExpression.getRight());
        return (_plus_10 + _generateJDBCExpression_9);
      }
    }
    if (!_matched) {
      if ((expression instanceof YComparisonExpression)) {
        _matched=true;
        final YComparisonExpression comparisonExpression = ((YComparisonExpression) expression);
        String operator = "?";
        String _op_1 = comparisonExpression.getOp();
        boolean _equals = Objects.equal(_op_1, ">=");
        if (_equals) {
          operator = ">=";
        } else {
          String _op_2 = comparisonExpression.getOp();
          boolean _equals_1 = Objects.equal(_op_2, "<=");
          if (_equals_1) {
            operator = "<=";
          } else {
            String _op_3 = comparisonExpression.getOp();
            boolean _equals_2 = Objects.equal(_op_3, ">");
            if (_equals_2) {
              operator = ">";
            } else {
              String _op_4 = comparisonExpression.getOp();
              boolean _equals_3 = Objects.equal(_op_4, "<");
              if (_equals_3) {
                operator = "<";
              }
            }
          }
        }
        String _generateJDBCExpression_10 = this.generateJDBCExpression(statement, comparisonExpression.getLeft());
        String _plus_11 = (_generateJDBCExpression_10 + " ");
        String _plus_12 = (_plus_11 + operator);
        String _plus_13 = (_plus_12 + " ");
        String _generateJDBCExpression_11 = this.generateJDBCExpression(statement, comparisonExpression.getRight());
        return (_plus_13 + _generateJDBCExpression_11);
      }
    }
    if (!_matched) {
      if ((expression instanceof YEqualityExpression)) {
        _matched=true;
        final YEqualityExpression equalityExpression = ((YEqualityExpression) expression);
        String operator_1 = "?";
        String _op_5 = equalityExpression.getOp();
        boolean _equals_4 = Objects.equal(_op_5, "==");
        if (_equals_4) {
          operator_1 = "=";
        } else {
          String _op_6 = equalityExpression.getOp();
          boolean _equals_5 = Objects.equal(_op_6, "!=");
          if (_equals_5) {
            operator_1 = "<>";
          }
        }
        String _generateJDBCExpression_12 = this.generateJDBCExpression(statement, equalityExpression.getLeft());
        String _plus_14 = (_generateJDBCExpression_12 + " ");
        String _plus_15 = (_plus_14 + operator_1);
        String _plus_16 = (_plus_15 + " ");
        String _generateJDBCExpression_13 = this.generateJDBCExpression(statement, equalityExpression.getRight());
        return (_plus_16 + _generateJDBCExpression_13);
      }
    }
    if (!_matched) {
      if ((expression instanceof YMemberSelection)) {
        _matched=true;
        final YMemberSelection memberSelection = ((YMemberSelection) expression);
        return this.generateJDBCMemberSelection(statement, memberSelection);
      }
    }
    if (!_matched) {
      if ((expression instanceof YSelf)) {
        _matched=true;
        return "this";
      }
    }
    if (!_matched) {
      if ((expression instanceof YNot)) {
        _matched=true;
        final YNot not = ((YNot) expression);
        String _generateJDBCExpression_14 = this.generateJDBCExpression(statement, not.getExpression());
        return ("!" + _generateJDBCExpression_14);
      }
    }
    if (!_matched) {
      if ((expression instanceof YBoolConstant)) {
        _matched=true;
        final YBoolConstant boolConstant = ((YBoolConstant) expression);
        return boolConstant.getValue();
      }
    }
    if (!_matched) {
      if ((expression instanceof YParenties)) {
        _matched=true;
        String _generateJDBCExpression_15 = this.generateJDBCExpression(statement, ((YParenties) expression).getA());
        String _plus_17 = ("(" + _generateJDBCExpression_15);
        return (_plus_17 + ")");
      }
    }
    if (!_matched) {
      if ((expression instanceof YSymbolRef)) {
        _matched=true;
        final YSymbolRef symbolRef = ((YSymbolRef) expression);
        return symbolRef.getSymbol().getName();
      }
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus_18 = ("\'" + _string);
          _xblockexpression_1 = (_plus_18 + "\'");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = "not done yet";
    }
    return _switchResult;
  }
  
  private String generateJDBCMemberSelection(final YStatement statement, final YMemberSelection memberSelection) {
    String _xblockexpression = null;
    {
      YExpression _receiver = memberSelection.getReceiver();
      final String propertyName = ((YMemberSelection) _receiver).getMember().getName();
      if ((statement instanceof YReadStatement)) {
        int index = 1;
        final YReadStatement readStatement = ((YReadStatement) statement);
        EList<YStructRefPair> _structs = readStatement.getStructs();
        for (final YStructRefPair structclass : _structs) {
          {
            final String name = structclass.getStructproperty().getName();
            final YAnnotTable table = this._langUtil.getImplementingTable(structclass.getStructclass());
            EList<YAnnotAbstractColumn> _columns = table.getColumns();
            for (final YAnnotAbstractColumn annotAbstractColumn : _columns) {
              if ((Objects.equal(propertyName, name) && 
                Objects.equal(((YAnnotColumn) annotAbstractColumn.getType()).getAttrref().getName(), memberSelection.getMember().getName()))) {
                String _name = annotAbstractColumn.getName();
                final String qualName = ((("T" + Integer.valueOf(index)) + ".") + _name);
                return qualName;
              }
            }
            index++;
          }
        }
        return "?";
      }
      _xblockexpression = "something went wrong";
    }
    return _xblockexpression;
  }
  
  private String generateMemberSelection(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    if (_isFunctioninvocation) {
      YMember _member = memberSelection.getMember();
      final YFunction function = ((YFunction) _member);
      YExpression _receiver = memberSelection.getReceiver();
      final String terminalExpression = this.generateTermination(((YMemberSelection) _receiver).getReceiver());
      YExpression _receiver_1 = memberSelection.getReceiver();
      String _name = ((YMemberSelection) _receiver_1).getMember().getName();
      String _plus = ((terminalExpression + ".") + _name);
      String _plus_1 = (_plus + ".");
      String _name_1 = function.getName();
      String _plus_2 = (_plus_1 + _name_1);
      String _generateFunctionArguments = this.generateFunctionArguments(memberSelection);
      return (_plus_2 + _generateFunctionArguments);
    } else {
      YExpression _receiver_2 = memberSelection.getReceiver();
      if ((_receiver_2 instanceof YMemberSelection)) {
        YExpression _receiver_3 = memberSelection.getReceiver();
        final String terminalExpression_1 = this.generateTermination(((YMemberSelection) _receiver_3).getReceiver());
        YExpression _receiver_4 = memberSelection.getReceiver();
        String _name_2 = ((YMemberSelection) _receiver_4).getMember().getName();
        final String text = ((terminalExpression_1 + ".") + _name_2);
        String _name_3 = memberSelection.getMember().getName();
        return ((text + ".") + _name_3);
      } else {
        final String terminalExpression_2 = this.generateTermination(memberSelection.getReceiver());
        String _name_4 = memberSelection.getMember().getName();
        final String text_1 = ((terminalExpression_2 + ".") + _name_4);
        return text_1;
      }
    }
  }
  
  private String generateFunctionArguments(final YMemberSelection memberSelection) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      EList<YExpression> _args = memberSelection.getArgs();
      boolean _hasElements = false;
      for(final YExpression arg : _args) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _generateExpression = this.generateExpression(arg);
        _builder.append(_generateExpression);
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  private String generateTermination(final YExpression expression) {
    String _switchResult = null;
    boolean _matched = false;
    if ((expression instanceof YSelf)) {
      _matched=true;
      return "this";
    }
    if (!_matched) {
      if ((expression instanceof YIntConstant)) {
        _matched=true;
        String _xblockexpression = null;
        {
          final YIntConstant intConstant = ((YIntConstant) expression);
          _xblockexpression = Integer.valueOf(intConstant.getValue()).toString();
        }
        _switchResult = _xblockexpression;
      }
    }
    if (!_matched) {
      if ((expression instanceof YStringConstant)) {
        _matched=true;
        String _xblockexpression_1 = null;
        {
          final YStringConstant stringConstant = ((YStringConstant) expression);
          String _string = stringConstant.getValue().toString();
          String _plus = ("\"" + _string);
          _xblockexpression_1 = (_plus + "\"");
        }
        _switchResult = _xblockexpression_1;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  private String generateSpecialFunctions(final YMemberSelection memberSelection) {
    boolean _isFunctioninvocation = memberSelection.isFunctioninvocation();
    boolean _not = (!_isFunctioninvocation);
    if (_not) {
      return "";
    }
    String _name = memberSelection.getMember().getName();
    boolean _equals = Objects.equal(_name, "setSubscript");
    if (_equals) {
      return this.generateFunctionSetSubscript(memberSelection);
    }
    String _name_1 = memberSelection.getMember().getName();
    boolean _equals_1 = Objects.equal(_name_1, "moveStruct");
    if (_equals_1) {
      return this._langGeneratorHelperForJava.generateFunctionMove(memberSelection);
    }
    String _name_2 = memberSelection.getMember().getName();
    boolean _equals_2 = Objects.equal(_name_2, "println");
    if (_equals_2) {
      return this.generateFunctionPrintln(memberSelection);
    }
    return "";
  }
  
  private String generateFunctionPrintln(final YMemberSelection memberSelection) {
    YExpression _get = memberSelection.getArgs().get(0);
    final YMemberSelection memberSelectionInner = ((YMemberSelection) _get);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// start of println()\t");
    _builder.newLine();
    _builder.append("System.out.println(");
    String _generateExpression = this.generateExpression(memberSelectionInner);
    _builder.append(_generateExpression);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("// end of println()\t");
    _builder.newLine();
    final String block = _builder.toString();
    return block;
  }
  
  private String generateFunctionSetSubscript(final YMemberSelection memberSelection) {
    YExpression _receiver = memberSelection.getReceiver();
    YMember _member = ((YMemberSelection) _receiver).getMember();
    final YProperty property = ((YProperty) _member);
    final String arrayName = property.getName();
    final YTuples tuples = property.getTuples();
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// start of setSubscript()\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(arrayName, "\t");
    _builder.append(".setSubscript(");
    String _generateExpression = this.generateExpression(memberSelection.getArgs().get(0));
    _builder.append(_generateExpression, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    {
      EList<YProperty> _includes = tuples.getIncludes();
      for(final YProperty include : _includes) {
        _builder.append("\t");
        _builder.append("this.");
        String _name = include.getName();
        _builder.append(_name, "\t");
        _builder.append(" = ");
        _builder.append(arrayName, "\t");
        _builder.append(".get");
        String _name_1 = include.getType().getName();
        _builder.append(_name_1, "\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("// end of setSubscript()\t");
    _builder.newLine();
    final String block = _builder.toString();
    return block;
  }
  
  public StringConcatenationClient _generateGetInstance(final YClass it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("public static ");
        IGeneratorNode __name = LangWidgetGeneratorForJava.this._langTraceExtensions._name(it);
        _builder.append(__name);
        _builder.append(" getInstance(DMWContext _context) {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("return new ");
        IGeneratorNode __name_1 = LangWidgetGeneratorForJava.this._langTraceExtensions._name(it);
        _builder.append(__name_1, "   ");
        _builder.append("(_context);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateConstructor(final YClass it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private ");
        IGeneratorNode __name = LangWidgetGeneratorForJava.this._langTraceExtensions._name(it);
        _builder.append(__name);
        _builder.append("(DMWContext _context) {");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("super(_context);");
        _builder.newLine();
        {
          EList<YMember> _members = it.getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YProperty)) {
                _builder.append("  ");
                IGeneratorNode _generateInitializeStructure = LangWidgetGeneratorForJava.this.generateInitializeStructure(it, ((YProperty) member));
                _builder.append(_generateInitializeStructure, "  ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateInnerClasses(final YClass clazz) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        {
          EList<YClass> _inners = clazz.getInners();
          for(final YClass innerclazz : _inners) {
            IGeneratorNode _generateClass = LangWidgetGeneratorForJava.this.generateClass(innerclazz, clazz.getName());
            _builder.append(_generateClass);
          }
        }
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateClass(final YClass it, final String widgetName) {
    StringConcatenationClient _xifexpression = null;
    if (((it.getSuperclass() != null) && Objects.equal(it.getSuperclass().getName(), "Structure"))) {
      StringConcatenationClient _xblockexpression = null;
      {
        this.registerImport("eu.jgen.notes.dmw.lite.runtimes.XStructure");
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
            _builder.append(_documentation);
            _builder.append("  ");
            _builder.newLineIfNotEmpty();
            _builder.append("public class ");
            IGeneratorNode __name = LangWidgetGeneratorForJava.this._langTraceExtensions._name(it);
            _builder.append(__name);
            _builder.append(" extends XStructure {");
            _builder.newLineIfNotEmpty();
            {
              EList<YMember> _members = it.getMembers();
              for(final YMember member : _members) {
                {
                  if ((member instanceof YProperty)) {
                    _builder.append("   ");
                    IGeneratorNode _generatePropertyForStructure = LangWidgetGeneratorForJava.this.generatePropertyForStructure(((YProperty) member));
                    _builder.append(_generatePropertyForStructure, "   ");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        };
        _xblockexpression = _client;
      }
      _xifexpression = _xblockexpression;
    } else {
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        }
      };
      _xifexpression = _client;
    }
    return _xifexpression;
  }
  
  public StringConcatenationClient _generatePropertyForStructure(final YProperty property) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(property);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("public ");
        String _translateTypeName = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.translateTypeName(property.getType().getName());
        _builder.append(_translateTypeName);
        _builder.append(" ");
        String _name = property.getName();
        _builder.append(_name);
        _builder.append(";");
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateProperties(final YClass it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        {
          EList<YMember> _members = it.getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YProperty)) {
                IGeneratorNode _generateProperty = LangWidgetGeneratorForJava.this.generateProperty(((YProperty) member));
                _builder.append(_generateProperty);
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateProperty(final YProperty it) {
    StringConcatenationClient _xblockexpression = null;
    {
      String _name = it.getType().getName();
      boolean _equals = Objects.equal(_name, "Array");
      if (_equals) {
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          }
        };
        return _client;
      }
      StringConcatenationClient _client_1 = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
          _builder.append(_documentation);
          _builder.append("  ");
          _builder.newLineIfNotEmpty();
          {
            YAccessLevel _access = it.getAccess();
            boolean _tripleNotEquals = (_access != null);
            if (_tripleNotEquals) {
              YAccessLevel _access_1 = it.getAccess();
              _builder.append(_access_1);
              _builder.append(" ");
            }
          }
          String _translateTypeName = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.translateTypeName(it.getType().getName());
          _builder.append(_translateTypeName);
          _builder.append(" ");
          IGeneratorNode __name = LangWidgetGeneratorForJava.this._langTraceExtensions._name(it);
          _builder.append(__name);
          _builder.append(";");
        }
      };
      _xblockexpression = _client_1;
    }
    return _xblockexpression;
  }
  
  public StringConcatenationClient _generateFunctions(final YClass it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        {
          EList<YMember> _members = it.getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YFunction)) {
                IGeneratorNode _generateFunctionForWidget = LangWidgetGeneratorForJava.this.generateFunctionForWidget(((YFunction) member));
                _builder.append(_generateFunctionForWidget);
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateFunctionForWidget(final YFunction it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.newLine();
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        YAccessLevel _access = it.getAccess();
        _builder.append(_access);
        _builder.append(" ");
        {
          YClass _type = it.getType();
          boolean _tripleNotEquals = (_type != null);
          if (_tripleNotEquals) {
            String _translateTypeName = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.translateTypeName(it.getType().getName());
            _builder.append(_translateTypeName);
          }
        }
        _builder.append(" ");
        {
          YClass _type_1 = it.getType();
          boolean _tripleEquals = (_type_1 == null);
          if (_tripleEquals) {
            _builder.append("void ");
          }
        }
        String _name = it.getName();
        _builder.append(_name);
        IGeneratorNode _generateFunctionParameters = LangWidgetGeneratorForJava.this.generateFunctionParameters(it);
        _builder.append(_generateFunctionParameters);
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getBody());
        _builder.append(_generateBlock, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateFunctionParameters(final YFunction it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("(");
        {
          EList<YParameter> _params = it.getParams();
          boolean _hasElements = false;
          for(final YParameter param : _params) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            String _translateTypeName = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.translateTypeName(param.getType().getName());
            _builder.append(_translateTypeName);
            _builder.append(" ");
            String _name = param.getName();
            _builder.append(_name);
          }
        }
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateArrays(final YClass it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        {
          ArrayList<YProperty> _findPropertiesOfTypeArray = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.findPropertiesOfTypeArray(it);
          for(final YProperty property : _findPropertiesOfTypeArray) {
            _builder.append("class ");
            String _firstUpper = StringExtensions.toFirstUpper(property.getName());
            _builder.append(_firstUpper);
            _builder.append(" extends XArray {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public ");
            String _firstUpper_1 = StringExtensions.toFirstUpper(property.getName());
            _builder.append(_firstUpper_1, "\t");
            _builder.append("(int max) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("super(max);");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
            {
              EList<YProperty> _includes = property.getTuples().getIncludes();
              for(final YProperty ref : _includes) {
                _builder.append("\t");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("SortedMap<Integer, ");
                String _firstUpper_2 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_2, "\t");
                _builder.append("> map");
                String _firstUpper_3 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_3, "\t");
                _builder.append(" = new ConcurrentSkipListMap<Integer, ");
                String _firstUpper_4 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_4, "\t");
                _builder.append(">();");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("public ");
                String _firstUpper_5 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_5, "\t");
                _builder.append(" get");
                String _firstUpper_6 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_6, "\t");
                _builder.append("() {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("if(super.getSubscript() == 0) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("return null;");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("if (map");
                String _firstUpper_7 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_7, "\t\t");
                _builder.append(".containsKey(super.getSubscript())) {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("return map");
                String _firstUpper_8 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_8, "\t\t\t");
                _builder.append(".get(super.getSubscript());");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("} else {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t\t");
                String _firstUpper_9 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_9, "\t\t\t");
                _builder.append(" ");
                String _name = ref.getName();
                _builder.append(_name, "\t\t\t");
                _builder.append(" = new ");
                String _firstUpper_10 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_10, "\t\t\t");
                _builder.append("();");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("map");
                String _firstUpper_11 = StringExtensions.toFirstUpper(ref.getType().getName());
                _builder.append(_firstUpper_11, "\t\t\t");
                _builder.append(".put(super.getSubscript(), ");
                IGeneratorNode __name = LangWidgetGeneratorForJava.this._langTraceExtensions._name(ref);
                _builder.append(__name, "\t\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("return ");
                String _name_1 = ref.getName();
                _builder.append(_name_1, "\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.newLine();
              }
            }
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
            String _firstUpper_12 = StringExtensions.toFirstUpper(property.getName());
            _builder.append(_firstUpper_12);
            _builder.append(" ");
            IGeneratorNode __name_1 = LangWidgetGeneratorForJava.this._langTraceExtensions._name(property);
            _builder.append(__name_1);
            _builder.append(" = new ");
            String _firstUpper_13 = StringExtensions.toFirstUpper(property.getName());
            _builder.append(_firstUpper_13);
            _builder.append("(");
            int _arraySize = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getArraySize(property);
            _builder.append(_arraySize);
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateBlock(final YBlock it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        {
          EList<YStatement> _statements = it.getStatements();
          for(final YStatement statement : _statements) {
            Object _selectStatementGeneration = LangWidgetGeneratorForJava.this.selectStatementGeneration(statement);
            _builder.append(_selectStatementGeneration);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementIf(final YIfStatement it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("if (");
        String _generateExpression = LangWidgetGeneratorForJava.this.generateExpression(it.getExpression());
        _builder.append(_generateExpression);
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getThenBlock());
        _builder.append(_generateBlock, "\t");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("} ");
        {
          YBlock _elseBlock = it.getElseBlock();
          boolean _tripleNotEquals = (_elseBlock != null);
          if (_tripleNotEquals) {
            _builder.append(" else {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t\t\t\t\t");
            IGeneratorNode _generateBlock_1 = LangWidgetGeneratorForJava.this.generateBlock(it.getElseBlock());
            _builder.append(_generateBlock_1, "\t\t\t\t\t\t\t\t");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementForIn(final YForInStatement it) {
    final String _index = this._langGeneratorHelperForJava.generateLocalName("_index");
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("int ");
        _builder.append(_index);
        _builder.append(" = 1;");
        _builder.newLineIfNotEmpty();
        _builder.append("for (");
        _builder.append(_index);
        _builder.append("=1; ");
        _builder.append(_index);
        _builder.append(" <= this.");
        String _name = it.getCollection().getName();
        _builder.append(_name);
        _builder.append(".getLast(); ");
        _builder.append(_index);
        _builder.append("++) {");
        _builder.newLineIfNotEmpty();
        String _name_1 = it.getCollection().getName();
        _builder.append(_name_1);
        _builder.append(".setSubscript(");
        _builder.append(_index);
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        {
          EList<YProperty> _includes = it.getCollection().getTuples().getIncludes();
          for(final YProperty include : _includes) {
            _builder.append("this.");
            String _name_2 = include.getName();
            _builder.append(_name_2);
            _builder.append(" = ");
            String _name_3 = it.getCollection().getName();
            _builder.append(_name_3);
            _builder.append(".get");
            String _name_4 = include.getType().getName();
            _builder.append(_name_4);
            _builder.append("();");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("   ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getBody());
        _builder.append(_generateBlock, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("}\t\t");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementReturn(final YReturn it) {
    YExpression _expression = it.getExpression();
    boolean _tripleEquals = (_expression == null);
    if (_tripleEquals) {
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
          _builder.append(_documentation);
          _builder.newLineIfNotEmpty();
          _builder.append("return;");
          _builder.newLine();
        }
      };
      return _client;
    } else {
      StringConcatenationClient _client_1 = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
          _builder.append(_documentation);
          _builder.newLineIfNotEmpty();
          _builder.append("return ");
          String _generateExpression = LangWidgetGeneratorForJava.this.generateExpression(it.getExpression());
          _builder.append(_generateExpression);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      };
      return _client_1;
    }
  }
  
  public StringConcatenationClient _generateStatementWhile(final YWhileStatement it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("while (");
        String _generateExpression = LangWidgetGeneratorForJava.this.generateExpression(it.getExpression());
        _builder.append(_generateExpression);
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getBody());
        _builder.append(_generateBlock, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("}\t\t\t");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementRepeatWhile(final YRepeatWhileStatement it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("  ");
        _builder.newLineIfNotEmpty();
        _builder.append("do {");
        _builder.newLine();
        _builder.append("      ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getBody());
        _builder.append(_generateBlock, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("} while (");
        String _generateExpression = LangWidgetGeneratorForJava.this.generateExpression(it.getExpression());
        _builder.append(_generateExpression);
        _builder.append(");\t\t");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementVariableDeclaration(final YVariableDeclaration it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        String _generateVariableDeclaration = LangWidgetGeneratorForJava.this.generateVariableDeclaration(it);
        _builder.append(_generateVariableDeclaration);
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementYAssignment(final YMemberSelection it) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        String _generateSpecialFunctions = LangWidgetGeneratorForJava.this.generateSpecialFunctions(it);
        _builder.append(_generateSpecialFunctions);
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementCreate(final YCreateStatement it) {
    final String _create = this._langGeneratorHelperForJava.generateLocalName("_create");
    this.innerFunctions2.add(this.generateStatementCreateFunction(it, _create));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.newLineIfNotEmpty();
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getSetBlock());
        _builder.append(_generateBlock);
        _builder.newLineIfNotEmpty();
        _builder.append(_create);
        _builder.append("();\t\t\t\t  ");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementCreateFunction(final YCreateStatement it, final String name) {
    final YAnnotTable implementingTable = this._langUtil.getImplementingTable(it.getStruct().getStructclass());
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(name);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("// Begin of Create Statement");
        _builder.newLine();
        _builder.append("   ");
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getSetBlock());
        _builder.append(_generateBlock, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("StringBuffer buffer = new StringBuffer();");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"INSERT INTO \\\"");
        String _name = implementingTable.getName();
        _builder.append(_name, "   ");
        _builder.append("\\\" (\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          EList<YMember> _members = it.getStruct().getStructproperty().getType().getMembers();
          boolean _hasElements = false;
          for(final YMember member : _members) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append("\\\"");
            String _implementingColumnName = LangWidgetGeneratorForJava.this._langUtil.getImplementingColumnName(implementingTable, member);
            _builder.append(_implementingColumnName, "   ");
            _builder.append("\\\"");
          }
        }
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\") VALUES (\");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          EList<YMember> _members_1 = it.getStruct().getStructproperty().getType().getMembers();
          boolean _hasElements_1 = false;
          for(final YMember member_1 : _members_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append("?");
          }
        }
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\")\");\t");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("System.out.println(buffer.toString());");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString());");
        _builder.newLine();
        _builder.append("      ");
        String _generateSetMethodsForCreateStatement = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateSetMethodsForCreateStatement(it);
        _builder.append(_generateSetMethodsForCreateStatement, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("_statement.execute();");
        _builder.newLine();
        _builder.append("      ");
        IGeneratorNode _generateBlock_1 = LangWidgetGeneratorForJava.this.generateBlock(it.getSuccess());
        _builder.append(_generateBlock_1, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("_statement.close();");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("return;");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("} catch (DerbySQLIntegrityConstraintViolationException e) {");
        _builder.newLine();
        _builder.append("      ");
        IGeneratorNode _generateBlock_2 = LangWidgetGeneratorForJava.this.generateBlock(it.getAlreadyExist());
        _builder.append(_generateBlock_2, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("} catch (SQLException e) {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("throw new DMWRuntimeException(e);");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("// End of Create Statement");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementAssociate(final YAssociateStatement it) {
    final String _associate = this._langGeneratorHelperForJava.generateLocalName("_associate");
    this.innerFunctions2.add(this.generateStatementAssociateFunction(it, _associate));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append(_associate);
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementAssociateFunction(final YAssociateStatement statement, final String name) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(name);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("// Begin of Associate Statement");
        _builder.newLine();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("// End of Associate Statement");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementDelete(final YDeleteStatement it) {
    final String _delete = this._langGeneratorHelperForJava.generateLocalName("_delete");
    this.innerFunctions2.add(this.generateStatementDeleteFunction(it, _delete));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append(_delete);
        _builder.append("(_rs);\t\t  ");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementDeleteFunction(final YDeleteStatement it, final String functionName) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(functionName);
        _builder.append("(ResultSet _rs) {\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("// begin of Delete Statement");
        _builder.newLine();
        _builder.append("   ");
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("_rs.deleteRow();");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("} catch (SQLException e) {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("throw new DMWRuntimeException(e);");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("// End of Delete Statement");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementUpdate(final YUpdateStatement it) {
    final String _update = this._langGeneratorHelperForJava.generateLocalName("_update");
    this.innerFunctions2.add(this.generateStatementUpdateFunction(it, _update));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append(_update);
        _builder.append("(_rs);\t\t  ");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementUpdateFunction(final YUpdateStatement it, final String functionName) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(functionName);
        _builder.append("(ResultSet _rs) {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("// begin of Update Statement");
        _builder.newLine();
        _builder.append("   ");
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getSetBlock());
        _builder.append(_generateBlock, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("      ");
        String _generateSetMethodsForUpdateStatement = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateSetMethodsForUpdateStatement(it);
        _builder.append(_generateSetMethodsForUpdateStatement, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("_rs.updateRow();");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("} catch (SQLException e) {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("throw new DMWRuntimeException(e);");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("// end of Update Statement");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementReadEach(final YReadEachStatement it) {
    final String _readEach = this._langGeneratorHelperForJava.generateLocalName("_read");
    this.innerFunctions2.add(this.generateStatementReadEachFunction(it, _readEach));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation);
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append(_readEach);
        _builder.append("();\t\t  ");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementReadEachFunction(final YReadEachStatement it, final String functionName) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(functionName);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("// Begin of Create Statement");
        _builder.newLine();
        _builder.append("   ");
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(it);
        _builder.append(_documentation, "   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("StringBuffer buffer = new StringBuffer();");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"SELECT \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          ArrayList<String> _createQualifiedColumnNamesListForReadEach = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.createQualifiedColumnNamesListForReadEach(it);
          boolean _hasElements = false;
          for(final String qualifiedName : _createQualifiedColumnNamesListForReadEach) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append(qualifiedName, "   ");
          }
        }
        _builder.append("\");\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" FROM \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          ArrayList<String> _generateFROMClause = LangWidgetGeneratorForJava.this.generateFROMClause(it);
          boolean _hasElements_1 = false;
          for(final String qualifiedName_1 : _generateFROMClause) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append(qualifiedName_1, "   ");
          }
        }
        _builder.append("\");\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" WHERE \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        String _generateJoinExpressionForReadEach = LangWidgetGeneratorForJava.this.generateJoinExpressionForReadEach(it);
        _builder.append(_generateJoinExpressionForReadEach, "   ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        String _generateJDBCExpression = LangWidgetGeneratorForJava.this.generateJDBCExpression(it, it.getWhereclause().getExpression());
        _builder.append(_generateJDBCExpression, "   ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" FOR UPDATE \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("System.out.println(buffer.toString());\t\t");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("ResultSet.CLOSE_CURSORS_AT_COMMIT);");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("_statement.setCursorName(\"viewF\");");
        _builder.newLine();
        _builder.append("      ");
        String _generateSetMethodsForReadEach = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateSetMethodsForReadEach(it);
        _builder.append(_generateSetMethodsForReadEach, "      ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("_statement.execute();");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("ResultSet _rs = _statement.getResultSet();");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("while (_rs.next()) {");
        _builder.newLine();
        _builder.append("         ");
        String _generateGetMethodsForReadEach = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateGetMethodsForReadEach(it);
        _builder.append(_generateGetMethodsForReadEach, "         ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(it.getSuccess());
        _builder.append(_generateBlock, "      ");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("_rs.close();");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("_statement.close();");
        _builder.newLine();
        _builder.append("      \t  ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("} catch (SQLException e) {");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("if(e.getSQLState() == \"23000\") {");
        _builder.newLine();
        _builder.append("           ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("throw new DMWRuntimeException(e);");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementRead(final YReadStatement readStatement) {
    final String _read = this._langGeneratorHelperForJava.generateLocalName("_read");
    this.innerFunctions2.add(this.generateStatementReadFunction(readStatement, _read));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        String _documentation = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.getDocumentation(readStatement);
        _builder.append(_documentation);
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append(_read);
        _builder.append("();\t\t  ");
        _builder.newLineIfNotEmpty();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateStatementReadFunction(final YReadStatement readStatement, final String _read) {
    readStatement.getStructs();
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(_read);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("StringBuffer buffer = new StringBuffer();");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"SELECT \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          ArrayList<String> _createQualifiedColumnNamesListForRead = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.createQualifiedColumnNamesListForRead(readStatement);
          boolean _hasElements = false;
          for(final String qualifiedName : _createQualifiedColumnNamesListForRead) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append(qualifiedName, "   ");
          }
        }
        _builder.append("\");\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" FROM \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        {
          ArrayList<String> _generateFROMClause = LangWidgetGeneratorForJava.this.generateFROMClause(readStatement);
          boolean _hasElements_1 = false;
          for(final String qualifiedName_1 : _generateFROMClause) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "   ");
            }
            _builder.append(qualifiedName_1, "   ");
          }
        }
        _builder.append("\");\t\t   ");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" WHERE \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        String _generateJoinExpressionForRead = LangWidgetGeneratorForJava.this.generateJoinExpressionForRead(readStatement);
        _builder.append(_generateJoinExpressionForRead, "   ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\"");
        String _generateJDBCExpression = LangWidgetGeneratorForJava.this.generateJDBCExpression(readStatement, readStatement.getWhereclause().getExpression());
        _builder.append(_generateJDBCExpression, "   ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("buffer.append(\" FOR UPDATE \");");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("System.out.println(buffer.toString());\t\t");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("PreparedStatement _statement = getContext().getConnection().prepareStatement(buffer.toString(), ResultSet.CONCUR_UPDATABLE,");
        _builder.newLine();
        _builder.append("   \t  \t\t\t\t\t");
        _builder.append("ResultSet.CLOSE_CURSORS_AT_COMMIT);");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("_statement.setCursorName(\"viewF\");");
        _builder.newLine();
        _builder.append("   \t  ");
        String _generateSetMethodsForRead = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateSetMethodsForRead(readStatement);
        _builder.append(_generateSetMethodsForRead, "   \t  ");
        _builder.newLineIfNotEmpty();
        _builder.append("   \t  ");
        _builder.append("_statement.execute();");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("ResultSet _rs = _statement.getResultSet();");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("if (_rs.next()) {");
        _builder.newLine();
        _builder.append("   \t  \t ");
        String _generateGetMethodsForRead = LangWidgetGeneratorForJava.this._langGeneratorHelperForJava.generateGetMethodsForRead(readStatement);
        _builder.append(_generateGetMethodsForRead, "   \t  \t ");
        _builder.newLineIfNotEmpty();
        _builder.append("   \t  \t  ");
        IGeneratorNode _generateBlock = LangWidgetGeneratorForJava.this.generateBlock(readStatement.getSuccess());
        _builder.append(_generateBlock, "   \t  \t  ");
        _builder.newLineIfNotEmpty();
        _builder.append("   \t  ");
        _builder.append("} else {");
        _builder.newLine();
        _builder.append("   \t  \t ");
        IGeneratorNode _generateBlock_1 = LangWidgetGeneratorForJava.this.generateBlock(readStatement.getNotfound());
        _builder.append(_generateBlock_1, "   \t  \t ");
        _builder.newLineIfNotEmpty();
        _builder.append("   \t  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("_rs.close();");
        _builder.newLine();
        _builder.append("   \t  ");
        _builder.append("_statement.close();\t\t\t   \t  ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("} catch (SQLException e) {");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("throw new DMWRuntimeException(e);");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
  
  public StringConcatenationClient _generateInitializeStructure(final YClass clazz, final YProperty property) {
    StringConcatenationClient _xblockexpression = null;
    {
      if ((((((Objects.equal(property.getType().getName(), "Array") || Objects.equal(property.getType().getName(), "Int")) || Objects.equal(property.getType().getName(), "Short")) || 
        Objects.equal(property.getType().getName(), "Decimal")) || Objects.equal(property.getType().getName(), "Long")) || Objects.equal(property.getType().getName(), "Bool"))) {
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          }
        };
        return _client;
      }
      final String _initStructure = this._langGeneratorHelperForJava.generateLocalName("_initStructure");
      this.innerFunctions2.add(this.generateInitStructureFunction(property, _initStructure));
      StringConcatenationClient _client_1 = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append(_initStructure);
          _builder.append("();");
        }
      };
      _xblockexpression = _client_1;
    }
    return _xblockexpression;
  }
  
  public StringConcatenationClient _generateInitStructureFunction(final YProperty property, final String methodName) {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("private void ");
        _builder.append(methodName);
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        String _name = property.getName();
        _builder.append(_name, "   ");
        _builder.append(" = new ");
        String _name_1 = property.getType().getName();
        _builder.append(_name_1, "   ");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
        {
          EList<YMember> _members = property.getType().getMembers();
          for(final YMember member : _members) {
            {
              if ((member instanceof YProperty)) {
                _builder.append("   ");
                String _generateInitializeProperty = LangWidgetGeneratorForJava.this.generateInitializeProperty(((YProperty) member), property.getName());
                _builder.append(_generateInitializeProperty, "   ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}\t");
        _builder.newLine();
      }
    };
    return _client;
  }
}
