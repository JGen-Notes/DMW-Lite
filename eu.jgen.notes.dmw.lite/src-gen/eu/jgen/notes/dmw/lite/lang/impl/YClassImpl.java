/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity;
import eu.jgen.notes.dmw.lite.lang.YClass;
import eu.jgen.notes.dmw.lite.lang.YMember;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>YClass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YClassImpl#getSuperclass <em>Superclass</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YClassImpl#getEntityRef <em>Entity Ref</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YClassImpl#getInners <em>Inners</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YClassImpl#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @generated
 */
public class YClassImpl extends YNamedElementImpl implements YClass
{
  /**
   * The cached value of the '{@link #getSuperclass() <em>Superclass</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperclass()
   * @generated
   * @ordered
   */
  protected YClass superclass;

  /**
   * The cached value of the '{@link #getEntityRef() <em>Entity Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntityRef()
   * @generated
   * @ordered
   */
  protected YAnnotEntity entityRef;

  /**
   * The cached value of the '{@link #getInners() <em>Inners</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInners()
   * @generated
   * @ordered
   */
  protected EList<YClass> inners;

  /**
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList<YMember> members;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected YClassImpl()
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
    return LangPackage.Literals.YCLASS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YClass getSuperclass()
  {
    if (superclass != null && superclass.eIsProxy())
    {
      InternalEObject oldSuperclass = (InternalEObject)superclass;
      superclass = (YClass)eResolveProxy(oldSuperclass);
      if (superclass != oldSuperclass)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YCLASS__SUPERCLASS, oldSuperclass, superclass));
      }
    }
    return superclass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YClass basicGetSuperclass()
  {
    return superclass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuperclass(YClass newSuperclass)
  {
    YClass oldSuperclass = superclass;
    superclass = newSuperclass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YCLASS__SUPERCLASS, oldSuperclass, superclass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntity getEntityRef()
  {
    if (entityRef != null && entityRef.eIsProxy())
    {
      InternalEObject oldEntityRef = (InternalEObject)entityRef;
      entityRef = (YAnnotEntity)eResolveProxy(oldEntityRef);
      if (entityRef != oldEntityRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YCLASS__ENTITY_REF, oldEntityRef, entityRef));
      }
    }
    return entityRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntity basicGetEntityRef()
  {
    return entityRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntityRef(YAnnotEntity newEntityRef)
  {
    YAnnotEntity oldEntityRef = entityRef;
    entityRef = newEntityRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YCLASS__ENTITY_REF, oldEntityRef, entityRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<YClass> getInners()
  {
    if (inners == null)
    {
      inners = new EObjectContainmentEList<YClass>(YClass.class, this, LangPackage.YCLASS__INNERS);
    }
    return inners;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<YMember> getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentEList<YMember>(YMember.class, this, LangPackage.YCLASS__MEMBERS);
    }
    return members;
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
      case LangPackage.YCLASS__INNERS:
        return ((InternalEList<?>)getInners()).basicRemove(otherEnd, msgs);
      case LangPackage.YCLASS__MEMBERS:
        return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
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
      case LangPackage.YCLASS__SUPERCLASS:
        if (resolve) return getSuperclass();
        return basicGetSuperclass();
      case LangPackage.YCLASS__ENTITY_REF:
        if (resolve) return getEntityRef();
        return basicGetEntityRef();
      case LangPackage.YCLASS__INNERS:
        return getInners();
      case LangPackage.YCLASS__MEMBERS:
        return getMembers();
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
      case LangPackage.YCLASS__SUPERCLASS:
        setSuperclass((YClass)newValue);
        return;
      case LangPackage.YCLASS__ENTITY_REF:
        setEntityRef((YAnnotEntity)newValue);
        return;
      case LangPackage.YCLASS__INNERS:
        getInners().clear();
        getInners().addAll((Collection<? extends YClass>)newValue);
        return;
      case LangPackage.YCLASS__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection<? extends YMember>)newValue);
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
      case LangPackage.YCLASS__SUPERCLASS:
        setSuperclass((YClass)null);
        return;
      case LangPackage.YCLASS__ENTITY_REF:
        setEntityRef((YAnnotEntity)null);
        return;
      case LangPackage.YCLASS__INNERS:
        getInners().clear();
        return;
      case LangPackage.YCLASS__MEMBERS:
        getMembers().clear();
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
      case LangPackage.YCLASS__SUPERCLASS:
        return superclass != null;
      case LangPackage.YCLASS__ENTITY_REF:
        return entityRef != null;
      case LangPackage.YCLASS__INNERS:
        return inners != null && !inners.isEmpty();
      case LangPackage.YCLASS__MEMBERS:
        return members != null && !members.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //YClassImpl
