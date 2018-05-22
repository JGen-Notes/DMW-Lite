/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>YAnnot Rel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotRelImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotRelImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotRelImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotRelImpl#isMany <em>Many</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YAnnotRelImpl#getInverse <em>Inverse</em>}</li>
 * </ul>
 *
 * @generated
 */
public class YAnnotRelImpl extends YAnnotEntityInnerImpl implements YAnnotRel
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptional()
   * @generated
   * @ordered
   */
  protected static final boolean OPTIONAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptional()
   * @generated
   * @ordered
   */
  protected boolean optional = OPTIONAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected YAnnotEntity target;

  /**
   * The default value of the '{@link #isMany() <em>Many</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMany()
   * @generated
   * @ordered
   */
  protected static final boolean MANY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMany() <em>Many</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMany()
   * @generated
   * @ordered
   */
  protected boolean many = MANY_EDEFAULT;

  /**
   * The cached value of the '{@link #getInverse() <em>Inverse</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInverse()
   * @generated
   * @ordered
   */
  protected YAnnotRel inverse;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected YAnnotRelImpl()
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
    return LangPackage.Literals.YANNOT_REL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_REL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOptional()
  {
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptional(boolean newOptional)
  {
    boolean oldOptional = optional;
    optional = newOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_REL__OPTIONAL, oldOptional, optional));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntity getTarget()
  {
    if (target != null && target.eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (YAnnotEntity)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YANNOT_REL__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntity basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(YAnnotEntity newTarget)
  {
    YAnnotEntity oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_REL__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMany()
  {
    return many;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMany(boolean newMany)
  {
    boolean oldMany = many;
    many = newMany;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_REL__MANY, oldMany, many));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotRel getInverse()
  {
    if (inverse != null && inverse.eIsProxy())
    {
      InternalEObject oldInverse = (InternalEObject)inverse;
      inverse = (YAnnotRel)eResolveProxy(oldInverse);
      if (inverse != oldInverse)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YANNOT_REL__INVERSE, oldInverse, inverse));
      }
    }
    return inverse;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotRel basicGetInverse()
  {
    return inverse;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInverse(YAnnotRel newInverse)
  {
    YAnnotRel oldInverse = inverse;
    inverse = newInverse;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YANNOT_REL__INVERSE, oldInverse, inverse));
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
      case LangPackage.YANNOT_REL__NAME:
        return getName();
      case LangPackage.YANNOT_REL__OPTIONAL:
        return isOptional();
      case LangPackage.YANNOT_REL__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case LangPackage.YANNOT_REL__MANY:
        return isMany();
      case LangPackage.YANNOT_REL__INVERSE:
        if (resolve) return getInverse();
        return basicGetInverse();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case LangPackage.YANNOT_REL__NAME:
        setName((String)newValue);
        return;
      case LangPackage.YANNOT_REL__OPTIONAL:
        setOptional((Boolean)newValue);
        return;
      case LangPackage.YANNOT_REL__TARGET:
        setTarget((YAnnotEntity)newValue);
        return;
      case LangPackage.YANNOT_REL__MANY:
        setMany((Boolean)newValue);
        return;
      case LangPackage.YANNOT_REL__INVERSE:
        setInverse((YAnnotRel)newValue);
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
      case LangPackage.YANNOT_REL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LangPackage.YANNOT_REL__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case LangPackage.YANNOT_REL__TARGET:
        setTarget((YAnnotEntity)null);
        return;
      case LangPackage.YANNOT_REL__MANY:
        setMany(MANY_EDEFAULT);
        return;
      case LangPackage.YANNOT_REL__INVERSE:
        setInverse((YAnnotRel)null);
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
      case LangPackage.YANNOT_REL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LangPackage.YANNOT_REL__OPTIONAL:
        return optional != OPTIONAL_EDEFAULT;
      case LangPackage.YANNOT_REL__TARGET:
        return target != null;
      case LangPackage.YANNOT_REL__MANY:
        return many != MANY_EDEFAULT;
      case LangPackage.YANNOT_REL__INVERSE:
        return inverse != null;
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", optional: ");
    result.append(optional);
    result.append(", many: ");
    result.append(many);
    result.append(')');
    return result.toString();
  }

} //YAnnotRelImpl