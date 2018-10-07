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
package eu.jgen.notes.dmw.lite.typing;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.typing.LangTypeComputer;
import eu.jgen.notes.dmw.lite.utility.LangLib;
import eu.jgen.notes.dmw.lite.utility.LangUtil;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class LangTypeConformance {
  @Inject
  @Extension
  private LangUtil _langUtil;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  public boolean isConformant(final YClass yclass1, final YClass yclass2) {
    return (((((yclass1 == LangTypeComputer.NULL_TYPE) || 
      (yclass1 == yclass2)) || 
      Objects.equal(this._iQualifiedNameProvider.getFullyQualifiedName(yclass2).toString(), LangLib.LIB_OBJECT)) || 
      this.conformToLibraryTypes(yclass1, yclass2)) || 
      this.isSubclassOf(yclass1, yclass2));
  }
  
  public boolean conformToLibraryTypes(final YClass yclass1, final YClass yclass2) {
    final boolean a = (((this.conformsToString(yclass1) && this.conformsToString(yclass2)) || (this.conformsToInt(yclass1) && this.conformsToInt(yclass2))) || (this.conformsToBoolean(yclass1) && this.conformsToBoolean(yclass2)));
    return a;
  }
  
  public boolean conformsToString(final YClass yclass) {
    return (Objects.equal(yclass, LangTypeComputer.STRING_TYPE) || Objects.equal(this._iQualifiedNameProvider.getFullyQualifiedName(yclass).toString(), LangLib.LIB_STRING));
  }
  
  public boolean conformsToInt(final YClass yclass) {
    return (Objects.equal(yclass, LangTypeComputer.INT_TYPE) || Objects.equal(this._iQualifiedNameProvider.getFullyQualifiedName(yclass).toString(), LangLib.LIB_INTEGER));
  }
  
  public boolean conformsToBoolean(final YClass yclass) {
    return (Objects.equal(yclass, LangTypeComputer.BOOLEAN_TYPE) || Objects.equal(this._iQualifiedNameProvider.getFullyQualifiedName(yclass).toString(), LangLib.LIB_BOOL));
  }
  
  public boolean isSubclassOf(final YClass yclass1, final YClass yclass2) {
    return this._langUtil.classHierarchy(yclass1).contains(yclass2);
  }
}
