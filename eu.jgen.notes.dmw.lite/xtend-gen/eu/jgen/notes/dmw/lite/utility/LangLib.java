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
package eu.jgen.notes.dmw.lite.utility;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.scoping.LangIndex;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class LangLib {
  @Inject
  @Extension
  private LangIndex _langIndex;
  
  public final static String MAIN_LIB = "eu/jgen/notes/lib/dmw/lang.dmw";
  
  public final static String LIB_PACKAGE = "eu.jgen.notes.lib.dmw";
  
  public final static String LIB_OBJECT = (LangLib.LIB_PACKAGE + ".Object");
  
  public final static String LIB_STRING = (LangLib.LIB_PACKAGE + ".String");
  
  public final static String LIB_INTEGER = (LangLib.LIB_PACKAGE + ".Int");
  
  public final static String LIB_SHORT = (LangLib.LIB_PACKAGE + ".Short");
  
  public final static String LIB_DATE = (LangLib.LIB_PACKAGE + ".Date");
  
  public final static String LIB_TIME = (LangLib.LIB_PACKAGE + ".Time");
  
  public final static String LIB_TIMESTAMP = (LangLib.LIB_PACKAGE + ".Timestamp");
  
  public final static String LIB_BOOLEAN = (LangLib.LIB_PACKAGE + ".Boolean");
  
  public final static String LIB_WIDGET = (LangLib.LIB_PACKAGE + ".Widget");
  
  public final static String LIB_EXIT = (LangLib.LIB_PACKAGE + ".Exit");
  
  private final static Logger LOG = Logger.getLogger(LangLib.class);
  
  public String loadLibSource() {
    try {
      String _xblockexpression = null;
      {
        final URL url = this.getClass().getClassLoader().getResource(LangLib.MAIN_LIB);
        final InputStream stream = url.openStream();
        InputStreamReader _inputStreamReader = new InputStreamReader(stream, Charsets.UTF_8);
        _xblockexpression = CharStreams.toString(_inputStreamReader);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void loadLib(final ResourceSet resourceSet) {
    try {
      final URL url = this.getClass().getClassLoader().getResource(LangLib.MAIN_LIB);
      final InputStream stream = url.openStream();
      final String urlPath = url.getPath();
      final Resource resource = resourceSet.createResource(URI.createFileURI(urlPath));
      LangLib.LOG.info(("loading library " + urlPath));
      resource.load(stream, resourceSet.getLoadOptions());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public YClass getLangObjectClass(final EObject context) {
    YClass _xblockexpression = null;
    {
      final Function1<IEObjectDescription, Boolean> _function = (IEObjectDescription it) -> {
        String _string = it.getQualifiedName().toString();
        return Boolean.valueOf(Objects.equal(_string, LangLib.LIB_OBJECT));
      };
      final IEObjectDescription desc = IterableExtensions.<IEObjectDescription>findFirst(this._langIndex.getVisibleClassesDescriptions(context), _function);
      if ((desc == null)) {
        return null;
      }
      EObject o = desc.getEObjectOrProxy();
      boolean _eIsProxy = o.eIsProxy();
      if (_eIsProxy) {
        o = context.eResource().getResourceSet().getEObject(desc.getEObjectURI(), true);
      }
      _xblockexpression = ((YClass) o);
    }
    return _xblockexpression;
  }
  
  /**
   * for Entities
   */
  public YAnnotEntity getLangObjectEntity(final EObject context) {
    YAnnotEntity _xblockexpression = null;
    {
      final Function1<IEObjectDescription, Boolean> _function = (IEObjectDescription it) -> {
        String _string = it.getQualifiedName().toString();
        return Boolean.valueOf(Objects.equal(_string, LangLib.LIB_OBJECT));
      };
      final IEObjectDescription desc = IterableExtensions.<IEObjectDescription>findFirst(this._langIndex.getVisibleEntityDescriptions(context), _function);
      if ((desc == null)) {
        return null;
      }
      EObject o = desc.getEObjectOrProxy();
      boolean _eIsProxy = o.eIsProxy();
      if (_eIsProxy) {
        o = context.eResource().getResourceSet().getEObject(desc.getEObjectURI(), true);
      }
      _xblockexpression = ((YAnnotEntity) o);
    }
    return _xblockexpression;
  }
}
