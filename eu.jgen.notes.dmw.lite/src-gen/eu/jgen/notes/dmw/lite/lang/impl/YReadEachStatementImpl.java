/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.LangPackage;
import eu.jgen.notes.dmw.lite.lang.YBlock;
import eu.jgen.notes.dmw.lite.lang.YJoin;
import eu.jgen.notes.dmw.lite.lang.YProperty;
import eu.jgen.notes.dmw.lite.lang.YReadEachStatement;
import eu.jgen.notes.dmw.lite.lang.YStructRefPair;
import eu.jgen.notes.dmw.lite.lang.YWhere;

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
 * An implementation of the model object '<em><b>YRead Each Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YReadEachStatementImpl#getStructs <em>Structs</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YReadEachStatementImpl#getJoinclause <em>Joinclause</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YReadEachStatementImpl#getWhereclause <em>Whereclause</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YReadEachStatementImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.impl.YReadEachStatementImpl#getSuccess <em>Success</em>}</li>
 * </ul>
 *
 * @generated
 */
public class YReadEachStatementImpl extends YStatementImpl implements YReadEachStatement
{
  /**
   * The cached value of the '{@link #getStructs() <em>Structs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructs()
   * @generated
   * @ordered
   */
  protected EList<YStructRefPair> structs;

  /**
   * The cached value of the '{@link #getJoinclause() <em>Joinclause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJoinclause()
   * @generated
   * @ordered
   */
  protected YJoin joinclause;

  /**
   * The cached value of the '{@link #getWhereclause() <em>Whereclause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhereclause()
   * @generated
   * @ordered
   */
  protected YWhere whereclause;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected YProperty target;

  /**
   * The cached value of the '{@link #getSuccess() <em>Success</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuccess()
   * @generated
   * @ordered
   */
  protected YBlock success;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected YReadEachStatementImpl()
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
    return LangPackage.Literals.YREAD_EACH_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<YStructRefPair> getStructs()
  {
    if (structs == null)
    {
      structs = new EObjectContainmentEList<YStructRefPair>(YStructRefPair.class, this, LangPackage.YREAD_EACH_STATEMENT__STRUCTS);
    }
    return structs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YJoin getJoinclause()
  {
    return joinclause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJoinclause(YJoin newJoinclause, NotificationChain msgs)
  {
    YJoin oldJoinclause = joinclause;
    joinclause = newJoinclause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE, oldJoinclause, newJoinclause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJoinclause(YJoin newJoinclause)
  {
    if (newJoinclause != joinclause)
    {
      NotificationChain msgs = null;
      if (joinclause != null)
        msgs = ((InternalEObject)joinclause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE, null, msgs);
      if (newJoinclause != null)
        msgs = ((InternalEObject)newJoinclause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE, null, msgs);
      msgs = basicSetJoinclause(newJoinclause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE, newJoinclause, newJoinclause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YWhere getWhereclause()
  {
    return whereclause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhereclause(YWhere newWhereclause, NotificationChain msgs)
  {
    YWhere oldWhereclause = whereclause;
    whereclause = newWhereclause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE, oldWhereclause, newWhereclause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhereclause(YWhere newWhereclause)
  {
    if (newWhereclause != whereclause)
    {
      NotificationChain msgs = null;
      if (whereclause != null)
        msgs = ((InternalEObject)whereclause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE, null, msgs);
      if (newWhereclause != null)
        msgs = ((InternalEObject)newWhereclause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE, null, msgs);
      msgs = basicSetWhereclause(newWhereclause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE, newWhereclause, newWhereclause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty getTarget()
  {
    if (target != null && target.eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (YProperty)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LangPackage.YREAD_EACH_STATEMENT__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(YProperty newTarget)
  {
    YProperty oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YBlock getSuccess()
  {
    return success;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSuccess(YBlock newSuccess, NotificationChain msgs)
  {
    YBlock oldSuccess = success;
    success = newSuccess;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__SUCCESS, oldSuccess, newSuccess);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuccess(YBlock newSuccess)
  {
    if (newSuccess != success)
    {
      NotificationChain msgs = null;
      if (success != null)
        msgs = ((InternalEObject)success).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__SUCCESS, null, msgs);
      if (newSuccess != null)
        msgs = ((InternalEObject)newSuccess).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LangPackage.YREAD_EACH_STATEMENT__SUCCESS, null, msgs);
      msgs = basicSetSuccess(newSuccess, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LangPackage.YREAD_EACH_STATEMENT__SUCCESS, newSuccess, newSuccess));
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
      case LangPackage.YREAD_EACH_STATEMENT__STRUCTS:
        return ((InternalEList<?>)getStructs()).basicRemove(otherEnd, msgs);
      case LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE:
        return basicSetJoinclause(null, msgs);
      case LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE:
        return basicSetWhereclause(null, msgs);
      case LangPackage.YREAD_EACH_STATEMENT__SUCCESS:
        return basicSetSuccess(null, msgs);
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
      case LangPackage.YREAD_EACH_STATEMENT__STRUCTS:
        return getStructs();
      case LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE:
        return getJoinclause();
      case LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE:
        return getWhereclause();
      case LangPackage.YREAD_EACH_STATEMENT__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case LangPackage.YREAD_EACH_STATEMENT__SUCCESS:
        return getSuccess();
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
      case LangPackage.YREAD_EACH_STATEMENT__STRUCTS:
        getStructs().clear();
        getStructs().addAll((Collection<? extends YStructRefPair>)newValue);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE:
        setJoinclause((YJoin)newValue);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE:
        setWhereclause((YWhere)newValue);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__TARGET:
        setTarget((YProperty)newValue);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__SUCCESS:
        setSuccess((YBlock)newValue);
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
      case LangPackage.YREAD_EACH_STATEMENT__STRUCTS:
        getStructs().clear();
        return;
      case LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE:
        setJoinclause((YJoin)null);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE:
        setWhereclause((YWhere)null);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__TARGET:
        setTarget((YProperty)null);
        return;
      case LangPackage.YREAD_EACH_STATEMENT__SUCCESS:
        setSuccess((YBlock)null);
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
      case LangPackage.YREAD_EACH_STATEMENT__STRUCTS:
        return structs != null && !structs.isEmpty();
      case LangPackage.YREAD_EACH_STATEMENT__JOINCLAUSE:
        return joinclause != null;
      case LangPackage.YREAD_EACH_STATEMENT__WHERECLAUSE:
        return whereclause != null;
      case LangPackage.YREAD_EACH_STATEMENT__TARGET:
        return target != null;
      case LangPackage.YREAD_EACH_STATEMENT__SUCCESS:
        return success != null;
    }
    return super.eIsSet(featureID);
  }

} //YReadEachStatementImpl
