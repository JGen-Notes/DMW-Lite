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

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.generator.LangGlobalCommandsGenerator;
import eu.jgen.notes.dmw.lite.generator.LangGlobalExitStatesGenerator;
import eu.jgen.notes.dmw.lite.generator.LangTechicalDesignGenerator;
import eu.jgen.notes.dmw.lite.generator.LangWidgetGeneratorForJava;
import eu.jgen.notes.dmw.lite.generator.LangWidgetGeneratorForSwift;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class LangGenerator extends AbstractGenerator {
  @Inject
  private LangTechicalDesignGenerator g1;
  
  @Inject
  private LangWidgetGeneratorForJava g2;
  
  @Inject
  private LangGlobalExitStatesGenerator g3;
  
  @Inject
  private LangGlobalCommandsGenerator g4;
  
  @Inject
  private LangWidgetGeneratorForSwift g5;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    this.g1.doGenerate(resource, fsa);
    this.g2.doGenerate(resource, fsa, context);
    this.g3.doGenerate(resource, fsa);
    this.g4.doGenerate(resource, fsa);
    this.g5.doGenerate(resource, fsa);
  }
}
