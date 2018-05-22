/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YAnnotRel;
import eu.jgen.notes.dmw.lite.lang.YJoinDef;
import eu.jgen.notes.dmw.lite.lang.YProperty;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>YJoin Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YJoinDefImpl#getFromView <em>From View</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YJoinDefImpl#getRelRef <em>Rel Ref</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YJoinDefImpl#getToView <em>To View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class YJoinDefImpl extends MinimalEObjectImpl.Container implements YJoinDef
{
  /**
   * The cached value of the '{@link #getFromView() <em>From View</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFromView()
   * @generated
   * @ordered
   */
  protected YProperty fromView;

  /**
   * The cached value of the '{@link #getRelRef() <em>Rel Ref</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelRef()
   * @generated
   * @ordered
   */
  protected EList<YAnnotRel> relRef;

  /**
   * The cached value of the '{@link #getToView() <em>To View</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToView()
   * @generated
   * @ordered
   */
  protected YProperty toView;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected YJoinDefImpl()
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
    return LangPackage.Literals.YJOIN_DEF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty getFromView()
  {
    if (fromView != null && fromView.eIsProxy())
    {
      InternalEObject oldFromView = (InternalEObject)fromView;
      fromView = (YProperty)eResolveProxy(oldFromView);
      if (fromView != oldFromView)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YJOIN_DEF__FROM_VIEW, oldFromView, fromView));
      }
    }
    return fromView;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty basicGetFromView()
  {
    return fromView;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFromView(YProperty newFromView)
  {
    YProperty oldFromView = fromView;
    fromView = newFromView;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YJOIN_DEF__FROM_VIEW, oldFromView, fromView));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<YAnnotRel> getRelRef()
  {
    if (relRef == null)
    {
      relRef = new EObjectResolvingEList<YAnnotRel>(YAnnotRel.class, this, LangPackage.YJOIN_DEF__REL_REF);
    }
    return relRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty getToView()
  {
    if (toView != null && toView.eIsProxy())
    {
      InternalEObject oldToView = (InternalEObject)toView;
      toView = (YProperty)eResolveProxy(oldToView);
      if (toView != oldToView)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YJOIN_DEF__TO_VIEW, oldToView, toView));
      }
    }
    return toView;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty basicGetToView()
  {
    return toView;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToView(YProperty newToView)
  {
    YProperty oldToView = toView;
    toView = newToView;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YJOIN_DEF__TO_VIEW, oldToView, toView));
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
      case LangPackage.YJOIN_DEF__FROM_VIEW:
        if (resolve) return getFromView();
        return basicGetFromView();
      case LangPackage.YJOIN_DEF__REL_REF:
        return getRelRef();
      case LangPackage.YJOIN_DEF__TO_VIEW:
        if (resolve) return getToView();
        return basicGetToView();
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
      case LangPackage.YJOIN_DEF__FROM_VIEW:
        setFromView((YProperty)newValue);
        return;
      case LangPackage.YJOIN_DEF__REL_REF:
        getRelRef().clear();
        getRelRef().addAll((Collection<? extends YAnnotRel>)newValue);
        return;
      case LangPackage.YJOIN_DEF__TO_VIEW:
        setToView((YProperty)newValue);
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
      case LangPackage.YJOIN_DEF__FROM_VIEW:
        setFromView((YProperty)null);
        return;
      case LangPackage.YJOIN_DEF__REL_REF:
        getRelRef().clear();
        return;
      case LangPackage.YJOIN_DEF__TO_VIEW:
        setToView((YProperty)null);
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
      case LangPackage.YJOIN_DEF__FROM_VIEW:
        return fromView != null;
      case LangPackage.YJOIN_DEF__REL_REF:
        return relRef != null && !relRef.isEmpty();
      case LangPackage.YJOIN_DEF__TO_VIEW:
        return toView != null;
    }
    return super.eIsSet(featureID);
  }

} //YJoinDefImpl