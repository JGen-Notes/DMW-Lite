/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnot;
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr;
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>YAnnot Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotColumnImpl#getAttrref <em>Attrref</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotColumnImpl#getType <em>Type</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotColumnImpl#getOptional <em>Optional</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotColumnImpl#getAnnots <em>Annots</em>}</li>
 * </ul>
 *
 * @generated
 */
public class YAnnotColumnImpl extends MinimalEObjectImpl.Container implements YAnnotColumn
{
  /**
   * The cached value of the '{@link #getAttrref() <em>Attrref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttrref()
   * @generated
   * @ordered
   */
  protected YAnnotAttr attrref;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected static final String OPTIONAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected String optional = OPTIONAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnnots() <em>Annots</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnots()
   * @generated
   * @ordered
   */
  protected EList<YAnnot> annots;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected YAnnotColumnImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return LangPackage.Literals.YANNOT_COLUMN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotAttr getAttrref()
  {
    if (attrref != null && attrref.eIsProxy())
    {
      InternalEObject oldAttrref = (InternalEObject)attrref;
      attrref = (YAnnotAttr)eResolveProxy(oldAttrref);
      if (attrref != oldAttrref)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YANNOT_COLUMN__ATTRREF, oldAttrref, attrref));
      }
    }
    return attrref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotAttr basicGetAttrref()
  {
    return attrref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttrref(YAnnotAttr newAttrref)
  {
    YAnnotAttr oldAttrref = attrref;
    attrref = newAttrref;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_COLUMN__ATTRREF, oldAttrref, attrref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_COLUMN__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOptional()
  {
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptional(String newOptional)
  {
    String oldOptional = optional;
    optional = newOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_COLUMN__OPTIONAL, oldOptional, optional));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<YAnnot> getAnnots()
  {
    if (annots == null)
    {
      annots = new EObjectContainmentEList<YAnnot>(YAnnot.class, this, LangPackage.YANNOT_COLUMN__ANNOTS);
    }
    return annots;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_COLUMN__ANNOTS:
        return ((InternalEList<?>)getAnnots()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_COLUMN__ATTRREF:
        if (resolve) return getAttrref();
        return basicGetAttrref();
      case LangPackage.YANNOT_COLUMN__TYPE:
        return getType();
      case LangPackage.YANNOT_COLUMN__OPTIONAL:
        return getOptional();
      case LangPackage.YANNOT_COLUMN__ANNOTS:
        return getAnnots();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_COLUMN__ATTRREF:
        setAttrref((YAnnotAttr)newValue);
        return;
      case LangPackage.YANNOT_COLUMN__TYPE:
        setType((String)newValue);
        return;
      case LangPackage.YANNOT_COLUMN__OPTIONAL:
        setOptional((String)newValue);
        return;
      case LangPackage.YANNOT_COLUMN__ANNOTS:
        getAnnots().clear();
        getAnnots().addAll((Collection<? extends YAnnot>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_COLUMN__ATTRREF:
        setAttrref((YAnnotAttr)null);
        return;
      case LangPackage.YANNOT_COLUMN__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case LangPackage.YANNOT_COLUMN__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case LangPackage.YANNOT_COLUMN__ANNOTS:
        getAnnots().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_COLUMN__ATTRREF:
        return attrref != null;
      case LangPackage.YANNOT_COLUMN__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case LangPackage.YANNOT_COLUMN__OPTIONAL:
        return OPTIONAL_EDEFAULT == null ? optional != null : !OPTIONAL_EDEFAULT.equals(optional);
      case LangPackage.YANNOT_COLUMN__ANNOTS:
        return annots != null && !annots.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (type: ");
    result.append(type);
    result.append(", optional: ");
    result.append(optional);
    result.append(')');
    return result.toString();
  }

} //YAnnotColumnImpl
