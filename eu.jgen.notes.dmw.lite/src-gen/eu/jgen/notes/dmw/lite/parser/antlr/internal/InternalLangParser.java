package eu.jgen.notes.dmw.lite.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import eu.jgen.notes.dmw.lite.services.LangGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalLangParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'package'", "';'", "'import'", "'.'", "'.*'", "'class'", "':'", "'->'", "'{'", "'}'", "'var'", "'?'", "'<'", "','", "'>'", "'func'", "'('", "')'", "'='", "'return'", "'if'", "'else'", "'switch'", "'default'", "'case'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", "'+'", "'-'", "'*'", "'/'", "'!'", "'true'", "'false'", "'self'", "'super'", "'null'", "'new'", "'db-read'", "'success'", "'not'", "'found'", "'db-read-each'", "'target'", "'db-create'", "'already'", "'exist'", "'db-update'", "'db-delete'", "'db-associate'", "'with'", "'db-disassociate'", "'from'", "'join'", "'where'", "'while'", "'repeat'", "'for'", "'in'", "'@length'", "'@max'", "'@decimal'", "'@default'", "'@action'", "'@message'", "'@msgtype'", "'@entity'", "'@attr'", "'@rel'", "'@parent'", "'<-'", "'@id'", "'@td'", "'database'", "'@table'", "'as'", "'VARCHAR'", "'CHAR'", "'INTEGER'", "'BIGINT'", "'SMALLINT'", "'DECIMAL'", "'TIME'", "'DATE'", "'TIMESTAMP'", "'BOOLEAN'", "'@column'", "'@primary'", "'@foreign'", "'@java'", "'uses'", "'@swift'", "'module'", "'@database'", "'private'", "'protected'", "'public'"
    };
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int RULE_INT=5;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__99=99;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public InternalLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalLangParser.tokenNames; }
    public String getGrammarFileName() { return "InternalLang.g"; }



     	private LangGrammarAccess grammarAccess;

        public InternalLangParser(TokenStream input, LangGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "YWidget";
       	}

       	@Override
       	protected LangGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleYWidget"
    // InternalLang.g:65:1: entryRuleYWidget returns [EObject current=null] : iv_ruleYWidget= ruleYWidget EOF ;
    public final EObject entryRuleYWidget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYWidget = null;


        try {
            // InternalLang.g:65:48: (iv_ruleYWidget= ruleYWidget EOF )
            // InternalLang.g:66:2: iv_ruleYWidget= ruleYWidget EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYWidgetRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYWidget=ruleYWidget();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYWidget; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYWidget"


    // $ANTLR start "ruleYWidget"
    // InternalLang.g:72:1: ruleYWidget returns [EObject current=null] : ( (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleYImport ) )* ( (lv_annotations_4_0= ruleYAnnotTop ) )* ( (lv_classes_5_0= ruleYClass ) )* ) ;
    public final EObject ruleYWidget() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_imports_3_0 = null;

        EObject lv_annotations_4_0 = null;

        EObject lv_classes_5_0 = null;



        	enterRule();

        try {
            // InternalLang.g:78:2: ( ( (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleYImport ) )* ( (lv_annotations_4_0= ruleYAnnotTop ) )* ( (lv_classes_5_0= ruleYClass ) )* ) )
            // InternalLang.g:79:2: ( (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleYImport ) )* ( (lv_annotations_4_0= ruleYAnnotTop ) )* ( (lv_classes_5_0= ruleYClass ) )* )
            {
            // InternalLang.g:79:2: ( (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleYImport ) )* ( (lv_annotations_4_0= ruleYAnnotTop ) )* ( (lv_classes_5_0= ruleYClass ) )* )
            // InternalLang.g:80:3: (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )? ( (lv_imports_3_0= ruleYImport ) )* ( (lv_annotations_4_0= ruleYAnnotTop ) )* ( (lv_classes_5_0= ruleYClass ) )*
            {
            // InternalLang.g:80:3: (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )? )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==11) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalLang.g:81:4: otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) (otherlv_2= ';' )?
                    {
                    otherlv_0=(Token)match(input,11,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getYWidgetAccess().getPackageKeyword_0_0());
                      			
                    }
                    // InternalLang.g:85:4: ( (lv_name_1_0= ruleQualifiedName ) )
                    // InternalLang.g:86:5: (lv_name_1_0= ruleQualifiedName )
                    {
                    // InternalLang.g:86:5: (lv_name_1_0= ruleQualifiedName )
                    // InternalLang.g:87:6: lv_name_1_0= ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYWidgetAccess().getNameQualifiedNameParserRuleCall_0_1_0());
                      					
                    }
                    pushFollow(FOLLOW_4);
                    lv_name_1_0=ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYWidgetRule());
                      						}
                      						set(
                      							current,
                      							"name",
                      							lv_name_1_0,
                      							"eu.jgen.notes.dmw.lite.Lang.QualifiedName");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalLang.g:104:4: (otherlv_2= ';' )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==12) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // InternalLang.g:105:5: otherlv_2= ';'
                            {
                            otherlv_2=(Token)match(input,12,FOLLOW_5); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getYWidgetAccess().getSemicolonKeyword_0_2());
                              				
                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalLang.g:111:3: ( (lv_imports_3_0= ruleYImport ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==13) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalLang.g:112:4: (lv_imports_3_0= ruleYImport )
            	    {
            	    // InternalLang.g:112:4: (lv_imports_3_0= ruleYImport )
            	    // InternalLang.g:113:5: lv_imports_3_0= ruleYImport
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYWidgetAccess().getImportsYImportParserRuleCall_1_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_5);
            	    lv_imports_3_0=ruleYImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYWidgetRule());
            	      					}
            	      					add(
            	      						current,
            	      						"imports",
            	      						lv_imports_3_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YImport");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalLang.g:130:3: ( (lv_annotations_4_0= ruleYAnnotTop ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==81||LA4_0==87||LA4_0==104||LA4_0==106||LA4_0==108) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalLang.g:131:4: (lv_annotations_4_0= ruleYAnnotTop )
            	    {
            	    // InternalLang.g:131:4: (lv_annotations_4_0= ruleYAnnotTop )
            	    // InternalLang.g:132:5: lv_annotations_4_0= ruleYAnnotTop
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYWidgetAccess().getAnnotationsYAnnotTopParserRuleCall_2_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_6);
            	    lv_annotations_4_0=ruleYAnnotTop();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYWidgetRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annotations",
            	      						lv_annotations_4_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnotTop");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalLang.g:149:3: ( (lv_classes_5_0= ruleYClass ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==16) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalLang.g:150:4: (lv_classes_5_0= ruleYClass )
            	    {
            	    // InternalLang.g:150:4: (lv_classes_5_0= ruleYClass )
            	    // InternalLang.g:151:5: lv_classes_5_0= ruleYClass
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYWidgetAccess().getClassesYClassParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_7);
            	    lv_classes_5_0=ruleYClass();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYWidgetRule());
            	      					}
            	      					add(
            	      						current,
            	      						"classes",
            	      						lv_classes_5_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YClass");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYWidget"


    // $ANTLR start "entryRuleYImport"
    // InternalLang.g:172:1: entryRuleYImport returns [EObject current=null] : iv_ruleYImport= ruleYImport EOF ;
    public final EObject entryRuleYImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYImport = null;


        try {
            // InternalLang.g:172:48: (iv_ruleYImport= ruleYImport EOF )
            // InternalLang.g:173:2: iv_ruleYImport= ruleYImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYImportRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYImport=ruleYImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYImport; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYImport"


    // $ANTLR start "ruleYImport"
    // InternalLang.g:179:1: ruleYImport returns [EObject current=null] : (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) otherlv_2= ';' ) ;
    public final EObject ruleYImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_importedNamespace_1_0 = null;



        	enterRule();

        try {
            // InternalLang.g:185:2: ( (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) otherlv_2= ';' ) )
            // InternalLang.g:186:2: (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) otherlv_2= ';' )
            {
            // InternalLang.g:186:2: (otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) otherlv_2= ';' )
            // InternalLang.g:187:3: otherlv_0= 'import' ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYImportAccess().getImportKeyword_0());
              		
            }
            // InternalLang.g:191:3: ( (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard ) )
            // InternalLang.g:192:4: (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard )
            {
            // InternalLang.g:192:4: (lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard )
            // InternalLang.g:193:5: lv_importedNamespace_1_0= ruleQualifiedNameWithWildcard
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_importedNamespace_1_0=ruleQualifiedNameWithWildcard();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYImportRule());
              					}
              					set(
              						current,
              						"importedNamespace",
              						lv_importedNamespace_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.QualifiedNameWithWildcard");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYImportAccess().getSemicolonKeyword_2());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYImport"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalLang.g:218:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalLang.g:218:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalLang.g:219:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalLang.g:225:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;

        AntlrDatatypeRuleToken this_ValidID_2 = null;



        	enterRule();

        try {
            // InternalLang.g:231:2: ( (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) )
            // InternalLang.g:232:2: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            {
            // InternalLang.g:232:2: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            // InternalLang.g:233:3: this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_9);
            this_ValidID_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_ValidID_0);
              		
            }
            if ( state.backtracking==0 ) {

              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:243:3: ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==14) && (synpred1_InternalLang())) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalLang.g:244:4: ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID
            	    {
            	    // InternalLang.g:244:4: ( ( '.' )=>kw= '.' )
            	    // InternalLang.g:245:5: ( '.' )=>kw= '.'
            	    {
            	    kw=(Token)match(input,14,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					current.merge(kw);
            	      					newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	      				
            	    }

            	    }

            	    if ( state.backtracking==0 ) {

            	      				newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1());
            	      			
            	    }
            	    pushFollow(FOLLOW_9);
            	    this_ValidID_2=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				current.merge(this_ValidID_2);
            	      			
            	    }
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleQualifiedNameWithWildcard"
    // InternalLang.g:267:1: entryRuleQualifiedNameWithWildcard returns [String current=null] : iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF ;
    public final String entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildcard = null;


        try {
            // InternalLang.g:267:65: (iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF )
            // InternalLang.g:268:2: iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedNameWithWildcard.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameWithWildcard"


    // $ANTLR start "ruleQualifiedNameWithWildcard"
    // InternalLang.g:274:1: ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildcard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalLang.g:280:2: ( (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) )
            // InternalLang.g:281:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            {
            // InternalLang.g:281:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            // InternalLang.g:282:3: this_QualifiedName_0= ruleQualifiedName (kw= '.*' )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_10);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_QualifiedName_0);
              		
            }
            if ( state.backtracking==0 ) {

              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:292:3: (kw= '.*' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalLang.g:293:4: kw= '.*'
                    {
                    kw=(Token)match(input,15,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameWithWildcard"


    // $ANTLR start "entryRuleValidID"
    // InternalLang.g:303:1: entryRuleValidID returns [String current=null] : iv_ruleValidID= ruleValidID EOF ;
    public final String entryRuleValidID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValidID = null;


        try {
            // InternalLang.g:303:47: (iv_ruleValidID= ruleValidID EOF )
            // InternalLang.g:304:2: iv_ruleValidID= ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleValidID=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValidID.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // InternalLang.g:310:1: ruleValidID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleValidID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalLang.g:316:2: (this_ID_0= RULE_ID )
            // InternalLang.g:317:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
              	
            }
            if ( state.backtracking==0 ) {

              		newLeafNode(this_ID_0, grammarAccess.getValidIDAccess().getIDTerminalRuleCall());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidID"


    // $ANTLR start "entryRuleYParameter"
    // InternalLang.g:327:1: entryRuleYParameter returns [EObject current=null] : iv_ruleYParameter= ruleYParameter EOF ;
    public final EObject entryRuleYParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYParameter = null;


        try {
            // InternalLang.g:327:51: (iv_ruleYParameter= ruleYParameter EOF )
            // InternalLang.g:328:2: iv_ruleYParameter= ruleYParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYParameter=ruleYParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYParameter; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYParameter"


    // $ANTLR start "ruleYParameter"
    // InternalLang.g:334:1: ruleYParameter returns [EObject current=null] : (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] | this_YJoinDef_1= ruleYJoinDef ) ;
    public final EObject ruleYParameter() throws RecognitionException {
        EObject current = null;

        EObject this_YTypedDeclaration_0 = null;

        EObject this_YJoinDef_1 = null;



        	enterRule();

        try {
            // InternalLang.g:340:2: ( (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] | this_YJoinDef_1= ruleYJoinDef ) )
            // InternalLang.g:341:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] | this_YJoinDef_1= ruleYJoinDef )
            {
            // InternalLang.g:341:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] | this_YJoinDef_1= ruleYJoinDef )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==17) ) {
                    alt8=1;
                }
                else if ( (LA8_1==18) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalLang.g:342:3: this_YTypedDeclaration_0= ruleYTypedDeclaration[$current]
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      				current = createModelElement(grammarAccess.getYParameterRule());
                      			}
                      			newCompositeNode(grammarAccess.getYParameterAccess().getYTypedDeclarationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YTypedDeclaration_0=ruleYTypedDeclaration(current);

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YTypedDeclaration_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:354:3: this_YJoinDef_1= ruleYJoinDef
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYParameterAccess().getYJoinDefParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YJoinDef_1=ruleYJoinDef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YJoinDef_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYParameter"


    // $ANTLR start "entryRuleYArgumentValue"
    // InternalLang.g:366:1: entryRuleYArgumentValue returns [String current=null] : iv_ruleYArgumentValue= ruleYArgumentValue EOF ;
    public final String entryRuleYArgumentValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleYArgumentValue = null;


        try {
            // InternalLang.g:366:54: (iv_ruleYArgumentValue= ruleYArgumentValue EOF )
            // InternalLang.g:367:2: iv_ruleYArgumentValue= ruleYArgumentValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYArgumentValueRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYArgumentValue=ruleYArgumentValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYArgumentValue.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYArgumentValue"


    // $ANTLR start "ruleYArgumentValue"
    // InternalLang.g:373:1: ruleYArgumentValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleYArgumentValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalLang.g:379:2: ( (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING ) )
            // InternalLang.g:380:2: (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING )
            {
            // InternalLang.g:380:2: (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_INT) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_STRING) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalLang.g:381:3: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(this_INT_0);
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newLeafNode(this_INT_0, grammarAccess.getYArgumentValueAccess().getINTTerminalRuleCall_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:389:3: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(this_STRING_1);
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newLeafNode(this_STRING_1, grammarAccess.getYArgumentValueAccess().getSTRINGTerminalRuleCall_1());
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYArgumentValue"


    // $ANTLR start "entryRuleYClass"
    // InternalLang.g:400:1: entryRuleYClass returns [EObject current=null] : iv_ruleYClass= ruleYClass EOF ;
    public final EObject entryRuleYClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYClass = null;


        try {
            // InternalLang.g:400:47: (iv_ruleYClass= ruleYClass EOF )
            // InternalLang.g:401:2: iv_ruleYClass= ruleYClass EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYClassRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYClass=ruleYClass();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYClass; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYClass"


    // $ANTLR start "ruleYClass"
    // InternalLang.g:407:1: ruleYClass returns [EObject current=null] : ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' ) ;
    public final EObject ruleYClass() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_inners_8_0 = null;

        EObject lv_members_9_0 = null;



        	enterRule();

        try {
            // InternalLang.g:413:2: ( ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' ) )
            // InternalLang.g:414:2: ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' )
            {
            // InternalLang.g:414:2: ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' )
            // InternalLang.g:415:3: () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}'
            {
            // InternalLang.g:415:3: ()
            // InternalLang.g:416:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYClassAccess().getYClassAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,16,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYClassAccess().getClassKeyword_1());
              		
            }
            // InternalLang.g:426:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:427:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:427:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:428:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYClassAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_11);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYClassRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:445:3: (otherlv_3= ':' ( ( ruleQualifiedName ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==17) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLang.g:446:4: otherlv_3= ':' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getYClassAccess().getColonKeyword_3_0());
                      			
                    }
                    // InternalLang.g:450:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:451:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:451:5: ( ruleQualifiedName )
                    // InternalLang.g:452:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYClassRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYClassAccess().getSuperclassYClassCrossReference_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_12);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:467:3: (otherlv_5= '->' ( ( ruleQualifiedName ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==18) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalLang.g:468:4: otherlv_5= '->' ( ( ruleQualifiedName ) )
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYClassAccess().getHyphenMinusGreaterThanSignKeyword_4_0());
                      			
                    }
                    // InternalLang.g:472:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:473:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:473:5: ( ruleQualifiedName )
                    // InternalLang.g:474:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYClassRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYClassAccess().getEntityRefYAnnotEntityCrossReference_4_1_0());
                      					
                    }
                    pushFollow(FOLLOW_13);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,19,FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYClassAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalLang.g:493:3: ( (lv_inners_8_0= ruleYClass ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==16) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalLang.g:494:4: (lv_inners_8_0= ruleYClass )
            	    {
            	    // InternalLang.g:494:4: (lv_inners_8_0= ruleYClass )
            	    // InternalLang.g:495:5: lv_inners_8_0= ruleYClass
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYClassAccess().getInnersYClassParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_14);
            	    lv_inners_8_0=ruleYClass();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"inners",
            	      						lv_inners_8_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YClass");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalLang.g:512:3: ( (lv_members_9_0= ruleYMember ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==21||LA13_0==26||(LA13_0>=109 && LA13_0<=111)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalLang.g:513:4: (lv_members_9_0= ruleYMember )
            	    {
            	    // InternalLang.g:513:4: (lv_members_9_0= ruleYMember )
            	    // InternalLang.g:514:5: lv_members_9_0= ruleYMember
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYClassAccess().getMembersYMemberParserRuleCall_7_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_15);
            	    lv_members_9_0=ruleYMember();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYClassRule());
            	      					}
            	      					add(
            	      						current,
            	      						"members",
            	      						lv_members_9_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YMember");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            otherlv_10=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_10, grammarAccess.getYClassAccess().getRightCurlyBracketKeyword_8());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYClass"


    // $ANTLR start "entryRuleYMember"
    // InternalLang.g:539:1: entryRuleYMember returns [EObject current=null] : iv_ruleYMember= ruleYMember EOF ;
    public final EObject entryRuleYMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYMember = null;


        try {
            // InternalLang.g:539:48: (iv_ruleYMember= ruleYMember EOF )
            // InternalLang.g:540:2: iv_ruleYMember= ruleYMember EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYMemberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYMember=ruleYMember();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYMember; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYMember"


    // $ANTLR start "ruleYMember"
    // InternalLang.g:546:1: ruleYMember returns [EObject current=null] : (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction ) ;
    public final EObject ruleYMember() throws RecognitionException {
        EObject current = null;

        EObject this_YProperty_0 = null;

        EObject this_YFunction_1 = null;



        	enterRule();

        try {
            // InternalLang.g:552:2: ( (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction ) )
            // InternalLang.g:553:2: (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction )
            {
            // InternalLang.g:553:2: (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction )
            int alt14=2;
            switch ( input.LA(1) ) {
            case 109:
                {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==21) ) {
                    alt14=1;
                }
                else if ( (LA14_1==26) ) {
                    alt14=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
                }
                break;
            case 110:
                {
                int LA14_2 = input.LA(2);

                if ( (LA14_2==26) ) {
                    alt14=2;
                }
                else if ( (LA14_2==21) ) {
                    alt14=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 2, input);

                    throw nvae;
                }
                }
                break;
            case 111:
                {
                int LA14_3 = input.LA(2);

                if ( (LA14_3==26) ) {
                    alt14=2;
                }
                else if ( (LA14_3==21) ) {
                    alt14=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 3, input);

                    throw nvae;
                }
                }
                break;
            case 21:
                {
                alt14=1;
                }
                break;
            case 26:
                {
                alt14=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalLang.g:554:3: this_YProperty_0= ruleYProperty
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYMemberAccess().getYPropertyParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YProperty_0=ruleYProperty();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YProperty_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:563:3: this_YFunction_1= ruleYFunction
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYMemberAccess().getYFunctionParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YFunction_1=ruleYFunction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YFunction_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYMember"


    // $ANTLR start "ruleYTypedDeclaration"
    // InternalLang.g:576:1: ruleYTypedDeclaration[EObject in_current] returns [EObject current=in_current] : ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) ) ;
    public final EObject ruleYTypedDeclaration(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalLang.g:582:2: ( ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) ) )
            // InternalLang.g:583:2: ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) )
            {
            // InternalLang.g:583:2: ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) )
            // InternalLang.g:584:3: ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) )
            {
            // InternalLang.g:584:3: ( (lv_name_0_0= ruleValidID ) )
            // InternalLang.g:585:4: (lv_name_0_0= ruleValidID )
            {
            // InternalLang.g:585:4: (lv_name_0_0= ruleValidID )
            // InternalLang.g:586:5: lv_name_0_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYTypedDeclarationAccess().getNameValidIDParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_16);
            lv_name_0_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYTypedDeclarationRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_0_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_1=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYTypedDeclarationAccess().getColonKeyword_1());
              		
            }
            // InternalLang.g:607:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:608:4: ( ruleQualifiedName )
            {
            // InternalLang.g:608:4: ( ruleQualifiedName )
            // InternalLang.g:609:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYTypedDeclarationRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYTypedDeclarationAccess().getTypeYClassCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYTypedDeclaration"


    // $ANTLR start "entryRuleYProperty"
    // InternalLang.g:627:1: entryRuleYProperty returns [EObject current=null] : iv_ruleYProperty= ruleYProperty EOF ;
    public final EObject entryRuleYProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYProperty = null;


        try {
            // InternalLang.g:627:50: (iv_ruleYProperty= ruleYProperty EOF )
            // InternalLang.g:628:2: iv_ruleYProperty= ruleYProperty EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYPropertyRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYProperty=ruleYProperty();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYProperty; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYProperty"


    // $ANTLR start "ruleYProperty"
    // InternalLang.g:634:1: ruleYProperty returns [EObject current=null] : ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' ) ;
    public final EObject ruleYProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_optional_4_0=null;
        Token otherlv_5=null;
        Token otherlv_8=null;
        Enumerator lv_access_0_0 = null;

        EObject this_YTypedDeclaration_2 = null;

        EObject lv_tuples_3_0 = null;

        EObject lv_annotations_7_0 = null;



        	enterRule();

        try {
            // InternalLang.g:640:2: ( ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' ) )
            // InternalLang.g:641:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' )
            {
            // InternalLang.g:641:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' )
            // InternalLang.g:642:3: ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';'
            {
            // InternalLang.g:642:3: ( (lv_access_0_0= ruleYAccessLevel ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=109 && LA15_0<=111)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalLang.g:643:4: (lv_access_0_0= ruleYAccessLevel )
                    {
                    // InternalLang.g:643:4: (lv_access_0_0= ruleYAccessLevel )
                    // InternalLang.g:644:5: lv_access_0_0= ruleYAccessLevel
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYPropertyAccess().getAccessYAccessLevelEnumRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_17);
                    lv_access_0_0=ruleYAccessLevel();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYPropertyRule());
                      					}
                      					set(
                      						current,
                      						"access",
                      						lv_access_0_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YAccessLevel");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,21,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYPropertyAccess().getVarKeyword_1());
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              				current = createModelElement(grammarAccess.getYPropertyRule());
              			}
              			newCompositeNode(grammarAccess.getYPropertyAccess().getYTypedDeclarationParserRuleCall_2());
              		
            }
            pushFollow(FOLLOW_18);
            this_YTypedDeclaration_2=ruleYTypedDeclaration(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YTypedDeclaration_2;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:676:3: ( (lv_tuples_3_0= ruleYTuples ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==23) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLang.g:677:4: (lv_tuples_3_0= ruleYTuples )
                    {
                    // InternalLang.g:677:4: (lv_tuples_3_0= ruleYTuples )
                    // InternalLang.g:678:5: lv_tuples_3_0= ruleYTuples
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYPropertyAccess().getTuplesYTuplesParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_19);
                    lv_tuples_3_0=ruleYTuples();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYPropertyRule());
                      					}
                      					set(
                      						current,
                      						"tuples",
                      						lv_tuples_3_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YTuples");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:695:3: ( (lv_optional_4_0= '?' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==22) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalLang.g:696:4: (lv_optional_4_0= '?' )
                    {
                    // InternalLang.g:696:4: (lv_optional_4_0= '?' )
                    // InternalLang.g:697:5: lv_optional_4_0= '?'
                    {
                    lv_optional_4_0=(Token)match(input,22,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_optional_4_0, grammarAccess.getYPropertyAccess().getOptionalQuestionMarkKeyword_4_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYPropertyRule());
                      					}
                      					setWithLastConsumed(current, "optional", true, "?");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:709:3: (otherlv_5= '->' ( ( ruleQualifiedName ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==18) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalLang.g:710:4: otherlv_5= '->' ( ( ruleQualifiedName ) )
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYPropertyAccess().getHyphenMinusGreaterThanSignKeyword_5_0());
                      			
                    }
                    // InternalLang.g:714:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:715:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:715:5: ( ruleQualifiedName )
                    // InternalLang.g:716:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYPropertyRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYPropertyAccess().getAttrRefYAnnotAttrCrossReference_5_1_0());
                      					
                    }
                    pushFollow(FOLLOW_21);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:731:3: ( (lv_annotations_7_0= ruleYAnnot ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=74 && LA19_0<=80)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalLang.g:732:4: (lv_annotations_7_0= ruleYAnnot )
            	    {
            	    // InternalLang.g:732:4: (lv_annotations_7_0= ruleYAnnot )
            	    // InternalLang.g:733:5: lv_annotations_7_0= ruleYAnnot
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYPropertyAccess().getAnnotationsYAnnotParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_21);
            	    lv_annotations_7_0=ruleYAnnot();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYPropertyRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annotations",
            	      						lv_annotations_7_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnot");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            otherlv_8=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getYPropertyAccess().getSemicolonKeyword_7());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYProperty"


    // $ANTLR start "entryRuleYTuples"
    // InternalLang.g:758:1: entryRuleYTuples returns [EObject current=null] : iv_ruleYTuples= ruleYTuples EOF ;
    public final EObject entryRuleYTuples() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYTuples = null;


        try {
            // InternalLang.g:758:48: (iv_ruleYTuples= ruleYTuples EOF )
            // InternalLang.g:759:2: iv_ruleYTuples= ruleYTuples EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYTuplesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYTuples=ruleYTuples();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYTuples; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYTuples"


    // $ANTLR start "ruleYTuples"
    // InternalLang.g:765:1: ruleYTuples returns [EObject current=null] : ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' ) ;
    public final EObject ruleYTuples() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalLang.g:771:2: ( ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' ) )
            // InternalLang.g:772:2: ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' )
            {
            // InternalLang.g:772:2: ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' )
            // InternalLang.g:773:3: () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>'
            {
            // InternalLang.g:773:3: ()
            // InternalLang.g:774:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYTuplesAccess().getYTuplesAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,23,FOLLOW_22); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYTuplesAccess().getLessThanSignKeyword_1());
              		
            }
            // InternalLang.g:784:3: ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_ID) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalLang.g:785:4: ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )*
                    {
                    // InternalLang.g:785:4: ( (otherlv_2= RULE_ID ) )
                    // InternalLang.g:786:5: (otherlv_2= RULE_ID )
                    {
                    // InternalLang.g:786:5: (otherlv_2= RULE_ID )
                    // InternalLang.g:787:6: otherlv_2= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYTuplesRule());
                      						}
                      					
                    }
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_2, grammarAccess.getYTuplesAccess().getIncludesYPropertyCrossReference_2_0_0());
                      					
                    }

                    }


                    }

                    // InternalLang.g:798:4: (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==24) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // InternalLang.g:799:5: otherlv_3= ',' ( (otherlv_4= RULE_ID ) )
                    	    {
                    	    otherlv_3=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getYTuplesAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalLang.g:803:5: ( (otherlv_4= RULE_ID ) )
                    	    // InternalLang.g:804:6: (otherlv_4= RULE_ID )
                    	    {
                    	    // InternalLang.g:804:6: (otherlv_4= RULE_ID )
                    	    // InternalLang.g:805:7: otherlv_4= RULE_ID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElement(grammarAccess.getYTuplesRule());
                    	      							}
                    	      						
                    	    }
                    	    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_23); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							newLeafNode(otherlv_4, grammarAccess.getYTuplesAccess().getIncludesYPropertyCrossReference_2_1_1_0());
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYTuplesAccess().getGreaterThanSignKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYTuples"


    // $ANTLR start "entryRuleYFunction"
    // InternalLang.g:826:1: entryRuleYFunction returns [EObject current=null] : iv_ruleYFunction= ruleYFunction EOF ;
    public final EObject entryRuleYFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYFunction = null;


        try {
            // InternalLang.g:826:50: (iv_ruleYFunction= ruleYFunction EOF )
            // InternalLang.g:827:2: iv_ruleYFunction= ruleYFunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYFunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYFunction=ruleYFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYFunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYFunction"


    // $ANTLR start "ruleYFunction"
    // InternalLang.g:833:1: ruleYFunction returns [EObject current=null] : ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) ) ;
    public final EObject ruleYFunction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_returnvalue_8_0=null;
        Enumerator lv_access_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_params_4_0 = null;

        EObject lv_params_6_0 = null;

        EObject lv_body_10_0 = null;



        	enterRule();

        try {
            // InternalLang.g:839:2: ( ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) ) )
            // InternalLang.g:840:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) )
            {
            // InternalLang.g:840:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) )
            // InternalLang.g:841:3: ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) )
            {
            // InternalLang.g:841:3: ( (lv_access_0_0= ruleYAccessLevel ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=109 && LA22_0<=111)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalLang.g:842:4: (lv_access_0_0= ruleYAccessLevel )
                    {
                    // InternalLang.g:842:4: (lv_access_0_0= ruleYAccessLevel )
                    // InternalLang.g:843:5: lv_access_0_0= ruleYAccessLevel
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYFunctionAccess().getAccessYAccessLevelEnumRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_24);
                    lv_access_0_0=ruleYAccessLevel();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYFunctionRule());
                      					}
                      					set(
                      						current,
                      						"access",
                      						lv_access_0_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YAccessLevel");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,26,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYFunctionAccess().getFuncKeyword_1());
              		
            }
            // InternalLang.g:864:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:865:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:865:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:866:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYFunctionAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_25);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYFunctionRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,27,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYFunctionAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalLang.g:887:3: ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_ID) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalLang.g:888:4: ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )*
                    {
                    // InternalLang.g:888:4: ( (lv_params_4_0= ruleYParameter ) )
                    // InternalLang.g:889:5: (lv_params_4_0= ruleYParameter )
                    {
                    // InternalLang.g:889:5: (lv_params_4_0= ruleYParameter )
                    // InternalLang.g:890:6: lv_params_4_0= ruleYParameter
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYFunctionAccess().getParamsYParameterParserRuleCall_4_0_0());
                      					
                    }
                    pushFollow(FOLLOW_27);
                    lv_params_4_0=ruleYParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYFunctionRule());
                      						}
                      						add(
                      							current,
                      							"params",
                      							lv_params_4_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YParameter");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalLang.g:907:4: (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==24) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // InternalLang.g:908:5: otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) )
                    	    {
                    	    otherlv_5=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getYFunctionAccess().getCommaKeyword_4_1_0());
                    	      				
                    	    }
                    	    // InternalLang.g:912:5: ( (lv_params_6_0= ruleYParameter ) )
                    	    // InternalLang.g:913:6: (lv_params_6_0= ruleYParameter )
                    	    {
                    	    // InternalLang.g:913:6: (lv_params_6_0= ruleYParameter )
                    	    // InternalLang.g:914:7: lv_params_6_0= ruleYParameter
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getYFunctionAccess().getParamsYParameterParserRuleCall_4_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_27);
                    	    lv_params_6_0=ruleYParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getYFunctionRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"params",
                    	      								lv_params_6_0,
                    	      								"eu.jgen.notes.dmw.lite.Lang.YParameter");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_7=(Token)match(input,28,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYFunctionAccess().getRightParenthesisKeyword_5());
              		
            }
            // InternalLang.g:937:3: ( (lv_returnvalue_8_0= '->' ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==18) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalLang.g:938:4: (lv_returnvalue_8_0= '->' )
                    {
                    // InternalLang.g:938:4: (lv_returnvalue_8_0= '->' )
                    // InternalLang.g:939:5: lv_returnvalue_8_0= '->'
                    {
                    lv_returnvalue_8_0=(Token)match(input,18,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_returnvalue_8_0, grammarAccess.getYFunctionAccess().getReturnvalueHyphenMinusGreaterThanSignKeyword_6_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYFunctionRule());
                      					}
                      					setWithLastConsumed(current, "returnvalue", true, "->");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:951:3: ( ( ruleQualifiedName ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalLang.g:952:4: ( ruleQualifiedName )
                    {
                    // InternalLang.g:952:4: ( ruleQualifiedName )
                    // InternalLang.g:953:5: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYFunctionRule());
                      					}
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYFunctionAccess().getTypeYClassCrossReference_7_0());
                      				
                    }
                    pushFollow(FOLLOW_28);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:967:3: ( (lv_body_10_0= ruleYBlock ) )
            // InternalLang.g:968:4: (lv_body_10_0= ruleYBlock )
            {
            // InternalLang.g:968:4: (lv_body_10_0= ruleYBlock )
            // InternalLang.g:969:5: lv_body_10_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYFunctionAccess().getBodyYBlockParserRuleCall_8_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_body_10_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYFunctionRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_10_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYFunction"


    // $ANTLR start "entryRuleYBlock"
    // InternalLang.g:990:1: entryRuleYBlock returns [EObject current=null] : iv_ruleYBlock= ruleYBlock EOF ;
    public final EObject entryRuleYBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYBlock = null;


        try {
            // InternalLang.g:990:47: (iv_ruleYBlock= ruleYBlock EOF )
            // InternalLang.g:991:2: iv_ruleYBlock= ruleYBlock EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYBlockRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYBlock=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYBlock; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYBlock"


    // $ANTLR start "ruleYBlock"
    // InternalLang.g:997:1: ruleYBlock returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' ) ;
    public final EObject ruleYBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_statements_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1003:2: ( ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' ) )
            // InternalLang.g:1004:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' )
            {
            // InternalLang.g:1004:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' )
            // InternalLang.g:1005:3: () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}'
            {
            // InternalLang.g:1005:3: ()
            // InternalLang.g:1006:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYBlockAccess().getYBlockAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,19,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYBlockAccess().getLeftCurlyBracketKeyword_1());
              		
            }
            // InternalLang.g:1016:3: ( (lv_statements_2_0= ruleYStatement ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=RULE_ID && LA27_0<=RULE_STRING)||(LA27_0>=30 && LA27_0<=31)||LA27_0==33||(LA27_0>=47 && LA27_0<=53)||LA27_0==57||LA27_0==59||(LA27_0>=62 && LA27_0<=64)||LA27_0==66||(LA27_0>=70 && LA27_0<=72)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalLang.g:1017:4: (lv_statements_2_0= ruleYStatement )
            	    {
            	    // InternalLang.g:1017:4: (lv_statements_2_0= ruleYStatement )
            	    // InternalLang.g:1018:5: lv_statements_2_0= ruleYStatement
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYBlockAccess().getStatementsYStatementParserRuleCall_2_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_29);
            	    lv_statements_2_0=ruleYStatement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYBlockRule());
            	      					}
            	      					add(
            	      						current,
            	      						"statements",
            	      						lv_statements_2_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YStatement");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            otherlv_3=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYBlockAccess().getRightCurlyBracketKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYBlock"


    // $ANTLR start "entryRuleYStatement"
    // InternalLang.g:1043:1: entryRuleYStatement returns [EObject current=null] : iv_ruleYStatement= ruleYStatement EOF ;
    public final EObject entryRuleYStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYStatement = null;


        try {
            // InternalLang.g:1043:51: (iv_ruleYStatement= ruleYStatement EOF )
            // InternalLang.g:1044:2: iv_ruleYStatement= ruleYStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYStatement=ruleYStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYStatement"


    // $ANTLR start "ruleYStatement"
    // InternalLang.g:1050:1: ruleYStatement returns [EObject current=null] : (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDisassociateStatement_6= ruleYDisassociateStatement | this_YDeleteStatement_7= ruleYDeleteStatement | this_YReadEachStatement_8= ruleYReadEachStatement | this_YWhileStatement_9= ruleYWhileStatement | this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement | this_YForInStatement_11= ruleYForInStatement | (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? ) | this_YIfStatement_14= ruleYIfStatement | this_YSwitchStatement_15= ruleYSwitchStatement ) ;
    public final EObject ruleYStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_13=null;
        EObject this_YVariableDeclaration_0 = null;

        EObject this_YReturn_1 = null;

        EObject this_YReadStatement_2 = null;

        EObject this_YCreateStatement_3 = null;

        EObject this_YUpdateStatement_4 = null;

        EObject this_YAssociateStatement_5 = null;

        EObject this_YDisassociateStatement_6 = null;

        EObject this_YDeleteStatement_7 = null;

        EObject this_YReadEachStatement_8 = null;

        EObject this_YWhileStatement_9 = null;

        EObject this_YRepeatWhileStatement_10 = null;

        EObject this_YForInStatement_11 = null;

        EObject this_YExpression_12 = null;

        EObject this_YIfStatement_14 = null;

        EObject this_YSwitchStatement_15 = null;



        	enterRule();

        try {
            // InternalLang.g:1056:2: ( (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDisassociateStatement_6= ruleYDisassociateStatement | this_YDeleteStatement_7= ruleYDeleteStatement | this_YReadEachStatement_8= ruleYReadEachStatement | this_YWhileStatement_9= ruleYWhileStatement | this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement | this_YForInStatement_11= ruleYForInStatement | (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? ) | this_YIfStatement_14= ruleYIfStatement | this_YSwitchStatement_15= ruleYSwitchStatement ) )
            // InternalLang.g:1057:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDisassociateStatement_6= ruleYDisassociateStatement | this_YDeleteStatement_7= ruleYDeleteStatement | this_YReadEachStatement_8= ruleYReadEachStatement | this_YWhileStatement_9= ruleYWhileStatement | this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement | this_YForInStatement_11= ruleYForInStatement | (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? ) | this_YIfStatement_14= ruleYIfStatement | this_YSwitchStatement_15= ruleYSwitchStatement )
            {
            // InternalLang.g:1057:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDisassociateStatement_6= ruleYDisassociateStatement | this_YDeleteStatement_7= ruleYDeleteStatement | this_YReadEachStatement_8= ruleYReadEachStatement | this_YWhileStatement_9= ruleYWhileStatement | this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement | this_YForInStatement_11= ruleYForInStatement | (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? ) | this_YIfStatement_14= ruleYIfStatement | this_YSwitchStatement_15= ruleYSwitchStatement )
            int alt29=15;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // InternalLang.g:1058:3: this_YVariableDeclaration_0= ruleYVariableDeclaration
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYVariableDeclarationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YVariableDeclaration_0=ruleYVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YVariableDeclaration_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:1067:3: this_YReturn_1= ruleYReturn
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYReturnParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YReturn_1=ruleYReturn();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YReturn_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:1076:3: this_YReadStatement_2= ruleYReadStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYReadStatementParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YReadStatement_2=ruleYReadStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YReadStatement_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:1085:3: this_YCreateStatement_3= ruleYCreateStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYCreateStatementParserRuleCall_3());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YCreateStatement_3=ruleYCreateStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YCreateStatement_3;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalLang.g:1094:3: this_YUpdateStatement_4= ruleYUpdateStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYUpdateStatementParserRuleCall_4());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YUpdateStatement_4=ruleYUpdateStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YUpdateStatement_4;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalLang.g:1103:3: this_YAssociateStatement_5= ruleYAssociateStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYAssociateStatementParserRuleCall_5());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAssociateStatement_5=ruleYAssociateStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAssociateStatement_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 7 :
                    // InternalLang.g:1112:3: this_YDisassociateStatement_6= ruleYDisassociateStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYDisassociateStatementParserRuleCall_6());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YDisassociateStatement_6=ruleYDisassociateStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YDisassociateStatement_6;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 8 :
                    // InternalLang.g:1121:3: this_YDeleteStatement_7= ruleYDeleteStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYDeleteStatementParserRuleCall_7());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YDeleteStatement_7=ruleYDeleteStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YDeleteStatement_7;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 9 :
                    // InternalLang.g:1130:3: this_YReadEachStatement_8= ruleYReadEachStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYReadEachStatementParserRuleCall_8());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YReadEachStatement_8=ruleYReadEachStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YReadEachStatement_8;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 10 :
                    // InternalLang.g:1139:3: this_YWhileStatement_9= ruleYWhileStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYWhileStatementParserRuleCall_9());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YWhileStatement_9=ruleYWhileStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YWhileStatement_9;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 11 :
                    // InternalLang.g:1148:3: this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYRepeatWhileStatementParserRuleCall_10());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YRepeatWhileStatement_10=ruleYRepeatWhileStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YRepeatWhileStatement_10;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 12 :
                    // InternalLang.g:1157:3: this_YForInStatement_11= ruleYForInStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYForInStatementParserRuleCall_11());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YForInStatement_11=ruleYForInStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YForInStatement_11;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 13 :
                    // InternalLang.g:1166:3: (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? )
                    {
                    // InternalLang.g:1166:3: (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? )
                    // InternalLang.g:1167:4: this_YExpression_12= ruleYExpression (otherlv_13= ';' )?
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getYStatementAccess().getYExpressionParserRuleCall_12_0());
                      			
                    }
                    pushFollow(FOLLOW_30);
                    this_YExpression_12=ruleYExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_YExpression_12;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    // InternalLang.g:1175:4: (otherlv_13= ';' )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==12) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalLang.g:1176:5: otherlv_13= ';'
                            {
                            otherlv_13=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_13, grammarAccess.getYStatementAccess().getSemicolonKeyword_12_1());
                              				
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 14 :
                    // InternalLang.g:1183:3: this_YIfStatement_14= ruleYIfStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYIfStatementParserRuleCall_13());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YIfStatement_14=ruleYIfStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YIfStatement_14;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 15 :
                    // InternalLang.g:1192:3: this_YSwitchStatement_15= ruleYSwitchStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYSwitchStatementParserRuleCall_14());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YSwitchStatement_15=ruleYSwitchStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YSwitchStatement_15;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYStatement"


    // $ANTLR start "entryRuleYVariableDeclaration"
    // InternalLang.g:1204:1: entryRuleYVariableDeclaration returns [EObject current=null] : iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF ;
    public final EObject entryRuleYVariableDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYVariableDeclaration = null;


        try {
            // InternalLang.g:1204:61: (iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF )
            // InternalLang.g:1205:2: iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYVariableDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYVariableDeclaration=ruleYVariableDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYVariableDeclaration; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYVariableDeclaration"


    // $ANTLR start "ruleYVariableDeclaration"
    // InternalLang.g:1211:1: ruleYVariableDeclaration returns [EObject current=null] : (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleYVariableDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_YTypedDeclaration_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1217:2: ( (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' ) )
            // InternalLang.g:1218:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' )
            {
            // InternalLang.g:1218:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' )
            // InternalLang.g:1219:3: this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';'
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              				current = createModelElement(grammarAccess.getYVariableDeclarationRule());
              			}
              			newCompositeNode(grammarAccess.getYVariableDeclarationAccess().getYTypedDeclarationParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_31);
            this_YTypedDeclaration_0=ruleYTypedDeclaration(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YTypedDeclaration_0;
              			afterParserOrEnumRuleCall();
              		
            }
            otherlv_1=(Token)match(input,29,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYVariableDeclarationAccess().getEqualsSignKeyword_1());
              		
            }
            // InternalLang.g:1234:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:1235:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:1235:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:1236:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYVariableDeclarationAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_expression_2_0=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYVariableDeclarationRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYVariableDeclarationAccess().getSemicolonKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYVariableDeclaration"


    // $ANTLR start "entryRuleYReturn"
    // InternalLang.g:1261:1: entryRuleYReturn returns [EObject current=null] : iv_ruleYReturn= ruleYReturn EOF ;
    public final EObject entryRuleYReturn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReturn = null;


        try {
            // InternalLang.g:1261:48: (iv_ruleYReturn= ruleYReturn EOF )
            // InternalLang.g:1262:2: iv_ruleYReturn= ruleYReturn EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYReturnRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYReturn=ruleYReturn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYReturn; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYReturn"


    // $ANTLR start "ruleYReturn"
    // InternalLang.g:1268:1: ruleYReturn returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' ) ;
    public final EObject ruleYReturn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1274:2: ( ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' ) )
            // InternalLang.g:1275:2: ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' )
            {
            // InternalLang.g:1275:2: ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' )
            // InternalLang.g:1276:3: () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';'
            {
            // InternalLang.g:1276:3: ()
            // InternalLang.g:1277:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYReturnAccess().getYReturnAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,30,FOLLOW_33); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYReturnAccess().getReturnKeyword_1());
              		
            }
            // InternalLang.g:1287:3: ( (lv_expression_2_0= ruleYOrExpression ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=RULE_ID && LA30_0<=RULE_STRING)||LA30_0==27||(LA30_0>=46 && LA30_0<=52)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalLang.g:1288:4: (lv_expression_2_0= ruleYOrExpression )
                    {
                    // InternalLang.g:1288:4: (lv_expression_2_0= ruleYOrExpression )
                    // InternalLang.g:1289:5: lv_expression_2_0= ruleYOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReturnAccess().getExpressionYOrExpressionParserRuleCall_2_0());
                      				
                    }
                    pushFollow(FOLLOW_8);
                    lv_expression_2_0=ruleYOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReturnRule());
                      					}
                      					set(
                      						current,
                      						"expression",
                      						lv_expression_2_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYReturnAccess().getSemicolonKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYReturn"


    // $ANTLR start "entryRuleYIfStatement"
    // InternalLang.g:1314:1: entryRuleYIfStatement returns [EObject current=null] : iv_ruleYIfStatement= ruleYIfStatement EOF ;
    public final EObject entryRuleYIfStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYIfStatement = null;


        try {
            // InternalLang.g:1314:53: (iv_ruleYIfStatement= ruleYIfStatement EOF )
            // InternalLang.g:1315:2: iv_ruleYIfStatement= ruleYIfStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYIfStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYIfStatement=ruleYIfStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYIfStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYIfStatement"


    // $ANTLR start "ruleYIfStatement"
    // InternalLang.g:1321:1: ruleYIfStatement returns [EObject current=null] : (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? ) ;
    public final EObject ruleYIfStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_expression_2_0 = null;

        EObject lv_thenBlock_4_0 = null;

        EObject lv_elseBlock_6_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1327:2: ( (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? ) )
            // InternalLang.g:1328:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? )
            {
            // InternalLang.g:1328:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? )
            // InternalLang.g:1329:3: otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )?
            {
            otherlv_0=(Token)match(input,31,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYIfStatementAccess().getIfKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYIfStatementAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:1337:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:1338:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:1338:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:1339:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYIfStatementAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_expression_2_0=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYIfStatementRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYIfStatementAccess().getRightParenthesisKeyword_3());
              		
            }
            // InternalLang.g:1360:3: ( (lv_thenBlock_4_0= ruleYBlock ) )
            // InternalLang.g:1361:4: (lv_thenBlock_4_0= ruleYBlock )
            {
            // InternalLang.g:1361:4: (lv_thenBlock_4_0= ruleYBlock )
            // InternalLang.g:1362:5: lv_thenBlock_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYIfStatementAccess().getThenBlockYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_35);
            lv_thenBlock_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYIfStatementRule());
              					}
              					set(
              						current,
              						"thenBlock",
              						lv_thenBlock_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:1379:3: ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==32) && (synpred2_InternalLang())) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalLang.g:1380:4: ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) )
                    {
                    // InternalLang.g:1380:4: ( ( 'else' )=>otherlv_5= 'else' )
                    // InternalLang.g:1381:5: ( 'else' )=>otherlv_5= 'else'
                    {
                    otherlv_5=(Token)match(input,32,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_5, grammarAccess.getYIfStatementAccess().getElseKeyword_5_0());
                      				
                    }

                    }

                    // InternalLang.g:1387:4: ( (lv_elseBlock_6_0= ruleYBlock ) )
                    // InternalLang.g:1388:5: (lv_elseBlock_6_0= ruleYBlock )
                    {
                    // InternalLang.g:1388:5: (lv_elseBlock_6_0= ruleYBlock )
                    // InternalLang.g:1389:6: lv_elseBlock_6_0= ruleYBlock
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYIfStatementAccess().getElseBlockYBlockParserRuleCall_5_1_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_elseBlock_6_0=ruleYBlock();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYIfStatementRule());
                      						}
                      						set(
                      							current,
                      							"elseBlock",
                      							lv_elseBlock_6_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YBlock");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYIfStatement"


    // $ANTLR start "entryRuleYSwitchStatement"
    // InternalLang.g:1411:1: entryRuleYSwitchStatement returns [EObject current=null] : iv_ruleYSwitchStatement= ruleYSwitchStatement EOF ;
    public final EObject entryRuleYSwitchStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSwitchStatement = null;


        try {
            // InternalLang.g:1411:57: (iv_ruleYSwitchStatement= ruleYSwitchStatement EOF )
            // InternalLang.g:1412:2: iv_ruleYSwitchStatement= ruleYSwitchStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYSwitchStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYSwitchStatement=ruleYSwitchStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYSwitchStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYSwitchStatement"


    // $ANTLR start "ruleYSwitchStatement"
    // InternalLang.g:1418:1: ruleYSwitchStatement returns [EObject current=null] : ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' ) ;
    public final EObject ruleYSwitchStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_switchExpression_2_0 = null;

        EObject lv_cases_4_0 = null;

        EObject lv_default_7_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1424:2: ( ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' ) )
            // InternalLang.g:1425:2: ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' )
            {
            // InternalLang.g:1425:2: ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' )
            // InternalLang.g:1426:3: () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}'
            {
            // InternalLang.g:1426:3: ()
            // InternalLang.g:1427:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYSwitchStatementAccess().getYSwitchStatementAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,33,FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYSwitchStatementAccess().getSwitchKeyword_1());
              		
            }
            // InternalLang.g:1437:3: ( (lv_switchExpression_2_0= ruleYExpression ) )
            // InternalLang.g:1438:4: (lv_switchExpression_2_0= ruleYExpression )
            {
            // InternalLang.g:1438:4: (lv_switchExpression_2_0= ruleYExpression )
            // InternalLang.g:1439:5: lv_switchExpression_2_0= ruleYExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYSwitchStatementAccess().getSwitchExpressionYExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_13);
            lv_switchExpression_2_0=ruleYExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYSwitchStatementRule());
              					}
              					set(
              						current,
              						"switchExpression",
              						lv_switchExpression_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_37); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYSwitchStatementAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:1460:3: ( (lv_cases_4_0= ruleYSwitchCase ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==17||LA32_0==35) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalLang.g:1461:4: (lv_cases_4_0= ruleYSwitchCase )
            	    {
            	    // InternalLang.g:1461:4: (lv_cases_4_0= ruleYSwitchCase )
            	    // InternalLang.g:1462:5: lv_cases_4_0= ruleYSwitchCase
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYSwitchStatementAccess().getCasesYSwitchCaseParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_37);
            	    lv_cases_4_0=ruleYSwitchCase();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYSwitchStatementRule());
            	      					}
            	      					add(
            	      						current,
            	      						"cases",
            	      						lv_cases_4_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YSwitchCase");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            // InternalLang.g:1479:3: (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==34) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalLang.g:1480:4: otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) )
                    {
                    otherlv_5=(Token)match(input,34,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYSwitchStatementAccess().getDefaultKeyword_5_0());
                      			
                    }
                    otherlv_6=(Token)match(input,17,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getYSwitchStatementAccess().getColonKeyword_5_1());
                      			
                    }
                    // InternalLang.g:1488:4: ( (lv_default_7_0= ruleYBlock ) )
                    // InternalLang.g:1489:5: (lv_default_7_0= ruleYBlock )
                    {
                    // InternalLang.g:1489:5: (lv_default_7_0= ruleYBlock )
                    // InternalLang.g:1490:6: lv_default_7_0= ruleYBlock
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYSwitchStatementAccess().getDefaultYBlockParserRuleCall_5_2_0());
                      					
                    }
                    pushFollow(FOLLOW_38);
                    lv_default_7_0=ruleYBlock();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYSwitchStatementRule());
                      						}
                      						set(
                      							current,
                      							"default",
                      							lv_default_7_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YBlock");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getYSwitchStatementAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYSwitchStatement"


    // $ANTLR start "entryRuleYSwitchCase"
    // InternalLang.g:1516:1: entryRuleYSwitchCase returns [EObject current=null] : iv_ruleYSwitchCase= ruleYSwitchCase EOF ;
    public final EObject entryRuleYSwitchCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSwitchCase = null;


        try {
            // InternalLang.g:1516:52: (iv_ruleYSwitchCase= ruleYSwitchCase EOF )
            // InternalLang.g:1517:2: iv_ruleYSwitchCase= ruleYSwitchCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYSwitchCaseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYSwitchCase=ruleYSwitchCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYSwitchCase; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYSwitchCase"


    // $ANTLR start "ruleYSwitchCase"
    // InternalLang.g:1523:1: ruleYSwitchCase returns [EObject current=null] : ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYSwitchCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_caseExpression_2_0 = null;

        EObject lv_then_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1529:2: ( ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:1530:2: ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:1530:2: ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) )
            // InternalLang.g:1531:3: () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) )
            {
            // InternalLang.g:1531:3: ()
            // InternalLang.g:1532:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYSwitchCaseAccess().getYSwitchCaseAction_0(),
              					current);
              			
            }

            }

            // InternalLang.g:1538:3: (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==35) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalLang.g:1539:4: otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) )
                    {
                    otherlv_1=(Token)match(input,35,FOLLOW_36); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getYSwitchCaseAccess().getCaseKeyword_1_0());
                      			
                    }
                    // InternalLang.g:1543:4: ( (lv_caseExpression_2_0= ruleYExpression ) )
                    // InternalLang.g:1544:5: (lv_caseExpression_2_0= ruleYExpression )
                    {
                    // InternalLang.g:1544:5: (lv_caseExpression_2_0= ruleYExpression )
                    // InternalLang.g:1545:6: lv_caseExpression_2_0= ruleYExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYSwitchCaseAccess().getCaseExpressionYExpressionParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
                    lv_caseExpression_2_0=ruleYExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYSwitchCaseRule());
                      						}
                      						set(
                      							current,
                      							"caseExpression",
                      							lv_caseExpression_2_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,17,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYSwitchCaseAccess().getColonKeyword_2());
              		
            }
            // InternalLang.g:1567:3: ( (lv_then_4_0= ruleYBlock ) )
            // InternalLang.g:1568:4: (lv_then_4_0= ruleYBlock )
            {
            // InternalLang.g:1568:4: (lv_then_4_0= ruleYBlock )
            // InternalLang.g:1569:5: lv_then_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYSwitchCaseAccess().getThenYBlockParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_then_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYSwitchCaseRule());
              					}
              					set(
              						current,
              						"then",
              						lv_then_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYSwitchCase"


    // $ANTLR start "entryRuleYSymbol"
    // InternalLang.g:1590:1: entryRuleYSymbol returns [EObject current=null] : iv_ruleYSymbol= ruleYSymbol EOF ;
    public final EObject entryRuleYSymbol() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSymbol = null;


        try {
            // InternalLang.g:1590:48: (iv_ruleYSymbol= ruleYSymbol EOF )
            // InternalLang.g:1591:2: iv_ruleYSymbol= ruleYSymbol EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYSymbolRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYSymbol=ruleYSymbol();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYSymbol; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYSymbol"


    // $ANTLR start "ruleYSymbol"
    // InternalLang.g:1597:1: ruleYSymbol returns [EObject current=null] : (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter ) ;
    public final EObject ruleYSymbol() throws RecognitionException {
        EObject current = null;

        EObject this_YVariableDeclaration_0 = null;

        EObject this_YParameter_1 = null;



        	enterRule();

        try {
            // InternalLang.g:1603:2: ( (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter ) )
            // InternalLang.g:1604:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )
            {
            // InternalLang.g:1604:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )
            int alt35=2;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // InternalLang.g:1605:3: this_YVariableDeclaration_0= ruleYVariableDeclaration
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYSymbolAccess().getYVariableDeclarationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YVariableDeclaration_0=ruleYVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YVariableDeclaration_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:1614:3: this_YParameter_1= ruleYParameter
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYSymbolAccess().getYParameterParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YParameter_1=ruleYParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YParameter_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYSymbol"


    // $ANTLR start "entryRuleYExpression"
    // InternalLang.g:1626:1: entryRuleYExpression returns [EObject current=null] : iv_ruleYExpression= ruleYExpression EOF ;
    public final EObject entryRuleYExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYExpression = null;


        try {
            // InternalLang.g:1626:52: (iv_ruleYExpression= ruleYExpression EOF )
            // InternalLang.g:1627:2: iv_ruleYExpression= ruleYExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYExpression=ruleYExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYExpression"


    // $ANTLR start "ruleYExpression"
    // InternalLang.g:1633:1: ruleYExpression returns [EObject current=null] : this_YAssignment_0= ruleYAssignment ;
    public final EObject ruleYExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAssignment_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1639:2: (this_YAssignment_0= ruleYAssignment )
            // InternalLang.g:1640:2: this_YAssignment_0= ruleYAssignment
            {
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getYExpressionAccess().getYAssignmentParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_YAssignment_0=ruleYAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_YAssignment_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYExpression"


    // $ANTLR start "entryRuleYAssignment"
    // InternalLang.g:1651:1: entryRuleYAssignment returns [EObject current=null] : iv_ruleYAssignment= ruleYAssignment EOF ;
    public final EObject entryRuleYAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAssignment = null;


        try {
            // InternalLang.g:1651:52: (iv_ruleYAssignment= ruleYAssignment EOF )
            // InternalLang.g:1652:2: iv_ruleYAssignment= ruleYAssignment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAssignmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAssignment=ruleYAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAssignment; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAssignment"


    // $ANTLR start "ruleYAssignment"
    // InternalLang.g:1658:1: ruleYAssignment returns [EObject current=null] : (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? ) ;
    public final EObject ruleYAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_YSelectionExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1664:2: ( (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? ) )
            // InternalLang.g:1665:2: (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? )
            {
            // InternalLang.g:1665:2: (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? )
            // InternalLang.g:1666:3: this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAssignmentAccess().getYSelectionExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_39);
            this_YSelectionExpression_0=ruleYSelectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YSelectionExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1674:3: ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==29) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalLang.g:1675:4: () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) )
                    {
                    // InternalLang.g:1675:4: ()
                    // InternalLang.g:1676:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getYAssignmentAccess().getYAssignmentLeftAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,29,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAssignmentAccess().getEqualsSignKeyword_1_1());
                      			
                    }
                    // InternalLang.g:1686:4: ( (lv_right_3_0= ruleYOrExpression ) )
                    // InternalLang.g:1687:5: (lv_right_3_0= ruleYOrExpression )
                    {
                    // InternalLang.g:1687:5: (lv_right_3_0= ruleYOrExpression )
                    // InternalLang.g:1688:6: lv_right_3_0= ruleYOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAssignmentAccess().getRightYOrExpressionParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=ruleYOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAssignmentRule());
                      						}
                      						set(
                      							current,
                      							"right",
                      							lv_right_3_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAssignment"


    // $ANTLR start "entryRuleYSelectionExpression"
    // InternalLang.g:1710:1: entryRuleYSelectionExpression returns [EObject current=null] : iv_ruleYSelectionExpression= ruleYSelectionExpression EOF ;
    public final EObject entryRuleYSelectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSelectionExpression = null;


        try {
            // InternalLang.g:1710:61: (iv_ruleYSelectionExpression= ruleYSelectionExpression EOF )
            // InternalLang.g:1711:2: iv_ruleYSelectionExpression= ruleYSelectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYSelectionExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYSelectionExpression=ruleYSelectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYSelectionExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYSelectionExpression"


    // $ANTLR start "ruleYSelectionExpression"
    // InternalLang.g:1717:1: ruleYSelectionExpression returns [EObject current=null] : (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* ) ;
    public final EObject ruleYSelectionExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_functioninvocation_4_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject this_YTerminalExpression_0 = null;

        EObject lv_args_5_0 = null;

        EObject lv_args_7_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1723:2: ( (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* ) )
            // InternalLang.g:1724:2: (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* )
            {
            // InternalLang.g:1724:2: (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* )
            // InternalLang.g:1725:3: this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYSelectionExpressionAccess().getYTerminalExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_9);
            this_YTerminalExpression_0=ruleYTerminalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YTerminalExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1733:3: ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==14) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalLang.g:1734:4: () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )?
            	    {
            	    // InternalLang.g:1734:4: ()
            	    // InternalLang.g:1735:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYSelectionExpressionAccess().getYMemberSelectionReceiverAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    otherlv_2=(Token)match(input,14,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getYSelectionExpressionAccess().getFullStopKeyword_1_1());
            	      			
            	    }
            	    // InternalLang.g:1745:4: ( (otherlv_3= RULE_ID ) )
            	    // InternalLang.g:1746:5: (otherlv_3= RULE_ID )
            	    {
            	    // InternalLang.g:1746:5: (otherlv_3= RULE_ID )
            	    // InternalLang.g:1747:6: otherlv_3= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getYSelectionExpressionRule());
            	      						}
            	      					
            	    }
            	    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_40); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(otherlv_3, grammarAccess.getYSelectionExpressionAccess().getMemberYMemberCrossReference_1_2_0());
            	      					
            	    }

            	    }


            	    }

            	    // InternalLang.g:1758:4: ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )?
            	    int alt39=2;
            	    int LA39_0 = input.LA(1);

            	    if ( (LA39_0==27) ) {
            	        alt39=1;
            	    }
            	    switch (alt39) {
            	        case 1 :
            	            // InternalLang.g:1759:5: ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')'
            	            {
            	            // InternalLang.g:1759:5: ( (lv_functioninvocation_4_0= '(' ) )
            	            // InternalLang.g:1760:6: (lv_functioninvocation_4_0= '(' )
            	            {
            	            // InternalLang.g:1760:6: (lv_functioninvocation_4_0= '(' )
            	            // InternalLang.g:1761:7: lv_functioninvocation_4_0= '('
            	            {
            	            lv_functioninvocation_4_0=(Token)match(input,27,FOLLOW_41); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_functioninvocation_4_0, grammarAccess.getYSelectionExpressionAccess().getFunctioninvocationLeftParenthesisKeyword_1_3_0_0());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getYSelectionExpressionRule());
            	              							}
            	              							setWithLastConsumed(current, "functioninvocation", true, "(");
            	              						
            	            }

            	            }


            	            }

            	            // InternalLang.g:1773:5: ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )?
            	            int alt38=2;
            	            int LA38_0 = input.LA(1);

            	            if ( ((LA38_0>=RULE_ID && LA38_0<=RULE_STRING)||LA38_0==27||(LA38_0>=46 && LA38_0<=52)) ) {
            	                alt38=1;
            	            }
            	            switch (alt38) {
            	                case 1 :
            	                    // InternalLang.g:1774:6: ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )*
            	                    {
            	                    // InternalLang.g:1774:6: ( (lv_args_5_0= ruleYOrExpression ) )
            	                    // InternalLang.g:1775:7: (lv_args_5_0= ruleYOrExpression )
            	                    {
            	                    // InternalLang.g:1775:7: (lv_args_5_0= ruleYOrExpression )
            	                    // InternalLang.g:1776:8: lv_args_5_0= ruleYOrExpression
            	                    {
            	                    if ( state.backtracking==0 ) {

            	                      								newCompositeNode(grammarAccess.getYSelectionExpressionAccess().getArgsYOrExpressionParserRuleCall_1_3_1_0_0());
            	                      							
            	                    }
            	                    pushFollow(FOLLOW_27);
            	                    lv_args_5_0=ruleYOrExpression();

            	                    state._fsp--;
            	                    if (state.failed) return current;
            	                    if ( state.backtracking==0 ) {

            	                      								if (current==null) {
            	                      									current = createModelElementForParent(grammarAccess.getYSelectionExpressionRule());
            	                      								}
            	                      								add(
            	                      									current,
            	                      									"args",
            	                      									lv_args_5_0,
            	                      									"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
            	                      								afterParserOrEnumRuleCall();
            	                      							
            	                    }

            	                    }


            	                    }

            	                    // InternalLang.g:1793:6: (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )*
            	                    loop37:
            	                    do {
            	                        int alt37=2;
            	                        int LA37_0 = input.LA(1);

            	                        if ( (LA37_0==24) ) {
            	                            alt37=1;
            	                        }


            	                        switch (alt37) {
            	                    	case 1 :
            	                    	    // InternalLang.g:1794:7: otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) )
            	                    	    {
            	                    	    otherlv_6=(Token)match(input,24,FOLLOW_32); if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      							newLeafNode(otherlv_6, grammarAccess.getYSelectionExpressionAccess().getCommaKeyword_1_3_1_1_0());
            	                    	      						
            	                    	    }
            	                    	    // InternalLang.g:1798:7: ( (lv_args_7_0= ruleYOrExpression ) )
            	                    	    // InternalLang.g:1799:8: (lv_args_7_0= ruleYOrExpression )
            	                    	    {
            	                    	    // InternalLang.g:1799:8: (lv_args_7_0= ruleYOrExpression )
            	                    	    // InternalLang.g:1800:9: lv_args_7_0= ruleYOrExpression
            	                    	    {
            	                    	    if ( state.backtracking==0 ) {

            	                    	      									newCompositeNode(grammarAccess.getYSelectionExpressionAccess().getArgsYOrExpressionParserRuleCall_1_3_1_1_1_0());
            	                    	      								
            	                    	    }
            	                    	    pushFollow(FOLLOW_27);
            	                    	    lv_args_7_0=ruleYOrExpression();

            	                    	    state._fsp--;
            	                    	    if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      									if (current==null) {
            	                    	      										current = createModelElementForParent(grammarAccess.getYSelectionExpressionRule());
            	                    	      									}
            	                    	      									add(
            	                    	      										current,
            	                    	      										"args",
            	                    	      										lv_args_7_0,
            	                    	      										"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
            	                    	      									afterParserOrEnumRuleCall();
            	                    	      								
            	                    	    }

            	                    	    }


            	                    	    }


            	                    	    }
            	                    	    break;

            	                    	default :
            	                    	    break loop37;
            	                        }
            	                    } while (true);


            	                    }
            	                    break;

            	            }

            	            otherlv_8=(Token)match(input,28,FOLLOW_9); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					newLeafNode(otherlv_8, grammarAccess.getYSelectionExpressionAccess().getRightParenthesisKeyword_1_3_2());
            	              				
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYSelectionExpression"


    // $ANTLR start "entryRuleYOrExpression"
    // InternalLang.g:1829:1: entryRuleYOrExpression returns [EObject current=null] : iv_ruleYOrExpression= ruleYOrExpression EOF ;
    public final EObject entryRuleYOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYOrExpression = null;


        try {
            // InternalLang.g:1829:54: (iv_ruleYOrExpression= ruleYOrExpression EOF )
            // InternalLang.g:1830:2: iv_ruleYOrExpression= ruleYOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYOrExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYOrExpression=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYOrExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYOrExpression"


    // $ANTLR start "ruleYOrExpression"
    // InternalLang.g:1836:1: ruleYOrExpression returns [EObject current=null] : (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* ) ;
    public final EObject ruleYOrExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAndExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1842:2: ( (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* ) )
            // InternalLang.g:1843:2: (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* )
            {
            // InternalLang.g:1843:2: (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* )
            // InternalLang.g:1844:3: this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYOrExpressionAccess().getYAndExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_42);
            this_YAndExpression_0=ruleYAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YAndExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1852:3: ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==36) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalLang.g:1853:4: () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) )
            	    {
            	    // InternalLang.g:1853:4: ()
            	    // InternalLang.g:1854:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYOrExpressionAccess().getYOrExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    if ( state.backtracking==0 ) {

            	      				newCompositeNode(grammarAccess.getYOrExpressionAccess().getOpOrParserRuleCall_1_1());
            	      			
            	    }
            	    pushFollow(FOLLOW_32);
            	    ruleOpOr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }
            	    // InternalLang.g:1867:4: ( (lv_right_3_0= ruleYAndExpression ) )
            	    // InternalLang.g:1868:5: (lv_right_3_0= ruleYAndExpression )
            	    {
            	    // InternalLang.g:1868:5: (lv_right_3_0= ruleYAndExpression )
            	    // InternalLang.g:1869:6: lv_right_3_0= ruleYAndExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYOrExpressionAccess().getRightYAndExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_42);
            	    lv_right_3_0=ruleYAndExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYOrExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YAndExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYOrExpression"


    // $ANTLR start "entryRuleOpOr"
    // InternalLang.g:1891:1: entryRuleOpOr returns [String current=null] : iv_ruleOpOr= ruleOpOr EOF ;
    public final String entryRuleOpOr() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOr = null;


        try {
            // InternalLang.g:1891:44: (iv_ruleOpOr= ruleOpOr EOF )
            // InternalLang.g:1892:2: iv_ruleOpOr= ruleOpOr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpOrRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpOr=ruleOpOr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpOr.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpOr"


    // $ANTLR start "ruleOpOr"
    // InternalLang.g:1898:1: ruleOpOr returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '||' ;
    public final AntlrDatatypeRuleToken ruleOpOr() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:1904:2: (kw= '||' )
            // InternalLang.g:1905:2: kw= '||'
            {
            kw=(Token)match(input,36,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(kw);
              		newLeafNode(kw, grammarAccess.getOpOrAccess().getVerticalLineVerticalLineKeyword());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpOr"


    // $ANTLR start "entryRuleYAndExpression"
    // InternalLang.g:1913:1: entryRuleYAndExpression returns [EObject current=null] : iv_ruleYAndExpression= ruleYAndExpression EOF ;
    public final EObject entryRuleYAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAndExpression = null;


        try {
            // InternalLang.g:1913:55: (iv_ruleYAndExpression= ruleYAndExpression EOF )
            // InternalLang.g:1914:2: iv_ruleYAndExpression= ruleYAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAndExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAndExpression=ruleYAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAndExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAndExpression"


    // $ANTLR start "ruleYAndExpression"
    // InternalLang.g:1920:1: ruleYAndExpression returns [EObject current=null] : (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* ) ;
    public final EObject ruleYAndExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YEqualityExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1926:2: ( (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* ) )
            // InternalLang.g:1927:2: (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* )
            {
            // InternalLang.g:1927:2: (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* )
            // InternalLang.g:1928:3: this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAndExpressionAccess().getYEqualityExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_43);
            this_YEqualityExpression_0=ruleYEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YEqualityExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1936:3: ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==37) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalLang.g:1937:4: () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) )
            	    {
            	    // InternalLang.g:1937:4: ()
            	    // InternalLang.g:1938:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYAndExpressionAccess().getYAndExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    if ( state.backtracking==0 ) {

            	      				newCompositeNode(grammarAccess.getYAndExpressionAccess().getOpAndParserRuleCall_1_1());
            	      			
            	    }
            	    pushFollow(FOLLOW_32);
            	    ruleOpAnd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }
            	    // InternalLang.g:1951:4: ( (lv_right_3_0= ruleYEqualityExpression ) )
            	    // InternalLang.g:1952:5: (lv_right_3_0= ruleYEqualityExpression )
            	    {
            	    // InternalLang.g:1952:5: (lv_right_3_0= ruleYEqualityExpression )
            	    // InternalLang.g:1953:6: lv_right_3_0= ruleYEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAndExpressionAccess().getRightYEqualityExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_43);
            	    lv_right_3_0=ruleYEqualityExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYAndExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YEqualityExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAndExpression"


    // $ANTLR start "entryRuleOpAnd"
    // InternalLang.g:1975:1: entryRuleOpAnd returns [String current=null] : iv_ruleOpAnd= ruleOpAnd EOF ;
    public final String entryRuleOpAnd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAnd = null;


        try {
            // InternalLang.g:1975:45: (iv_ruleOpAnd= ruleOpAnd EOF )
            // InternalLang.g:1976:2: iv_ruleOpAnd= ruleOpAnd EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpAndRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpAnd=ruleOpAnd();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpAnd.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpAnd"


    // $ANTLR start "ruleOpAnd"
    // InternalLang.g:1982:1: ruleOpAnd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '&&' ;
    public final AntlrDatatypeRuleToken ruleOpAnd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:1988:2: (kw= '&&' )
            // InternalLang.g:1989:2: kw= '&&'
            {
            kw=(Token)match(input,37,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(kw);
              		newLeafNode(kw, grammarAccess.getOpAndAccess().getAmpersandAmpersandKeyword());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpAnd"


    // $ANTLR start "entryRuleYEqualityExpression"
    // InternalLang.g:1997:1: entryRuleYEqualityExpression returns [EObject current=null] : iv_ruleYEqualityExpression= ruleYEqualityExpression EOF ;
    public final EObject entryRuleYEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYEqualityExpression = null;


        try {
            // InternalLang.g:1997:60: (iv_ruleYEqualityExpression= ruleYEqualityExpression EOF )
            // InternalLang.g:1998:2: iv_ruleYEqualityExpression= ruleYEqualityExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYEqualityExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYEqualityExpression=ruleYEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYEqualityExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYEqualityExpression"


    // $ANTLR start "ruleYEqualityExpression"
    // InternalLang.g:2004:1: ruleYEqualityExpression returns [EObject current=null] : (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* ) ;
    public final EObject ruleYEqualityExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_YComparisonExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2010:2: ( (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* ) )
            // InternalLang.g:2011:2: (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* )
            {
            // InternalLang.g:2011:2: (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* )
            // InternalLang.g:2012:3: this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYEqualityExpressionAccess().getYComparisonExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_44);
            this_YComparisonExpression_0=ruleYComparisonExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YComparisonExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2020:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>=38 && LA44_0<=39)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalLang.g:2021:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) )
            	    {
            	    // InternalLang.g:2021:4: ()
            	    // InternalLang.g:2022:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYEqualityExpressionAccess().getYEqualityExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2028:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalLang.g:2029:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalLang.g:2029:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalLang.g:2030:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalLang.g:2030:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt43=2;
            	    int LA43_0 = input.LA(1);

            	    if ( (LA43_0==38) ) {
            	        alt43=1;
            	    }
            	    else if ( (LA43_0==39) ) {
            	        alt43=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 43, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt43) {
            	        case 1 :
            	            // InternalLang.g:2031:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,38,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_1, grammarAccess.getYEqualityExpressionAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getYEqualityExpressionRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              						
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalLang.g:2042:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,39,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_2, grammarAccess.getYEqualityExpressionAccess().getOpExclamationMarkEqualsSignKeyword_1_1_0_1());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getYEqualityExpressionRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              						
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalLang.g:2055:4: ( (lv_right_3_0= ruleYComparisonExpression ) )
            	    // InternalLang.g:2056:5: (lv_right_3_0= ruleYComparisonExpression )
            	    {
            	    // InternalLang.g:2056:5: (lv_right_3_0= ruleYComparisonExpression )
            	    // InternalLang.g:2057:6: lv_right_3_0= ruleYComparisonExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYEqualityExpressionAccess().getRightYComparisonExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_44);
            	    lv_right_3_0=ruleYComparisonExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYEqualityExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YComparisonExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYEqualityExpression"


    // $ANTLR start "entryRuleYComparisonExpression"
    // InternalLang.g:2079:1: entryRuleYComparisonExpression returns [EObject current=null] : iv_ruleYComparisonExpression= ruleYComparisonExpression EOF ;
    public final EObject entryRuleYComparisonExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYComparisonExpression = null;


        try {
            // InternalLang.g:2079:62: (iv_ruleYComparisonExpression= ruleYComparisonExpression EOF )
            // InternalLang.g:2080:2: iv_ruleYComparisonExpression= ruleYComparisonExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYComparisonExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYComparisonExpression=ruleYComparisonExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYComparisonExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYComparisonExpression"


    // $ANTLR start "ruleYComparisonExpression"
    // InternalLang.g:2086:1: ruleYComparisonExpression returns [EObject current=null] : (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* ) ;
    public final EObject ruleYComparisonExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAdditiveExpression_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2092:2: ( (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* ) )
            // InternalLang.g:2093:2: (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* )
            {
            // InternalLang.g:2093:2: (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* )
            // InternalLang.g:2094:3: this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getYAdditiveExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_45);
            this_YAdditiveExpression_0=ruleYAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YAdditiveExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2102:3: ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==23||LA45_0==25||(LA45_0>=40 && LA45_0<=41)) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalLang.g:2103:4: () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) )
            	    {
            	    // InternalLang.g:2103:4: ()
            	    // InternalLang.g:2104:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYComparisonExpressionAccess().getYComparisonExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2110:4: ( (lv_op_2_0= ruleOpCompare ) )
            	    // InternalLang.g:2111:5: (lv_op_2_0= ruleOpCompare )
            	    {
            	    // InternalLang.g:2111:5: (lv_op_2_0= ruleOpCompare )
            	    // InternalLang.g:2112:6: lv_op_2_0= ruleOpCompare
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getOpOpCompareParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_32);
            	    lv_op_2_0=ruleOpCompare();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYComparisonExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"op",
            	      							lv_op_2_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.OpCompare");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalLang.g:2129:4: ( (lv_right_3_0= ruleYAdditiveExpression ) )
            	    // InternalLang.g:2130:5: (lv_right_3_0= ruleYAdditiveExpression )
            	    {
            	    // InternalLang.g:2130:5: (lv_right_3_0= ruleYAdditiveExpression )
            	    // InternalLang.g:2131:6: lv_right_3_0= ruleYAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getRightYAdditiveExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_45);
            	    lv_right_3_0=ruleYAdditiveExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYComparisonExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YAdditiveExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYComparisonExpression"


    // $ANTLR start "entryRuleOpCompare"
    // InternalLang.g:2153:1: entryRuleOpCompare returns [String current=null] : iv_ruleOpCompare= ruleOpCompare EOF ;
    public final String entryRuleOpCompare() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpCompare = null;


        try {
            // InternalLang.g:2153:49: (iv_ruleOpCompare= ruleOpCompare EOF )
            // InternalLang.g:2154:2: iv_ruleOpCompare= ruleOpCompare EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpCompareRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpCompare=ruleOpCompare();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpCompare.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOpCompare"


    // $ANTLR start "ruleOpCompare"
    // InternalLang.g:2160:1: ruleOpCompare returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) ;
    public final AntlrDatatypeRuleToken ruleOpCompare() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:2166:2: ( (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) )
            // InternalLang.g:2167:2: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            {
            // InternalLang.g:2167:2: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            int alt46=4;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt46=1;
                }
                break;
            case 41:
                {
                alt46=2;
                }
                break;
            case 25:
                {
                alt46=3;
                }
                break;
            case 23:
                {
                alt46=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // InternalLang.g:2168:3: kw= '>='
                    {
                    kw=(Token)match(input,40,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignEqualsSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:2174:3: kw= '<='
                    {
                    kw=(Token)match(input,41,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignEqualsSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:2180:3: kw= '>'
                    {
                    kw=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:2186:3: kw= '<'
                    {
                    kw=(Token)match(input,23,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignKeyword_3());
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpCompare"


    // $ANTLR start "entryRuleYAdditiveExpression"
    // InternalLang.g:2195:1: entryRuleYAdditiveExpression returns [EObject current=null] : iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF ;
    public final EObject entryRuleYAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAdditiveExpression = null;


        try {
            // InternalLang.g:2195:60: (iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF )
            // InternalLang.g:2196:2: iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAdditiveExpression=ruleYAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAdditiveExpression"


    // $ANTLR start "ruleYAdditiveExpression"
    // InternalLang.g:2202:1: ruleYAdditiveExpression returns [EObject current=null] : (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleYAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_YMultiplicativeExpression_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2208:2: ( (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* ) )
            // InternalLang.g:2209:2: (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* )
            {
            // InternalLang.g:2209:2: (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* )
            // InternalLang.g:2210:3: this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAdditiveExpressionAccess().getYMultiplicativeExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_46);
            this_YMultiplicativeExpression_0=ruleYMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YMultiplicativeExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2218:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=42 && LA48_0<=43)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalLang.g:2219:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) )
            	    {
            	    // InternalLang.g:2219:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( (LA47_0==42) ) {
            	        alt47=1;
            	    }
            	    else if ( (LA47_0==43) ) {
            	        alt47=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 47, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt47) {
            	        case 1 :
            	            // InternalLang.g:2220:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalLang.g:2220:5: ( () otherlv_2= '+' )
            	            // InternalLang.g:2221:6: () otherlv_2= '+'
            	            {
            	            // InternalLang.g:2221:6: ()
            	            // InternalLang.g:2222:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getYAdditiveExpressionAccess().getYPlusLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_2=(Token)match(input,42,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_2, grammarAccess.getYAdditiveExpressionAccess().getPlusSignKeyword_1_0_0_1());
            	              					
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalLang.g:2234:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalLang.g:2234:5: ( () otherlv_4= '-' )
            	            // InternalLang.g:2235:6: () otherlv_4= '-'
            	            {
            	            // InternalLang.g:2235:6: ()
            	            // InternalLang.g:2236:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getYAdditiveExpressionAccess().getYMinusLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_4=(Token)match(input,43,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_4, grammarAccess.getYAdditiveExpressionAccess().getHyphenMinusKeyword_1_0_1_1());
            	              					
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // InternalLang.g:2248:4: ( (lv_right_5_0= ruleYMultiplicativeExpression ) )
            	    // InternalLang.g:2249:5: (lv_right_5_0= ruleYMultiplicativeExpression )
            	    {
            	    // InternalLang.g:2249:5: (lv_right_5_0= ruleYMultiplicativeExpression )
            	    // InternalLang.g:2250:6: lv_right_5_0= ruleYMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAdditiveExpressionAccess().getRightYMultiplicativeExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_46);
            	    lv_right_5_0=ruleYMultiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYAdditiveExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_5_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YMultiplicativeExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAdditiveExpression"


    // $ANTLR start "entryRuleYMultiplicativeExpression"
    // InternalLang.g:2272:1: entryRuleYMultiplicativeExpression returns [EObject current=null] : iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF ;
    public final EObject entryRuleYMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYMultiplicativeExpression = null;


        try {
            // InternalLang.g:2272:66: (iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF )
            // InternalLang.g:2273:2: iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYMultiplicativeExpression=ruleYMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYMultiplicativeExpression"


    // $ANTLR start "ruleYMultiplicativeExpression"
    // InternalLang.g:2279:1: ruleYMultiplicativeExpression returns [EObject current=null] : (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* ) ;
    public final EObject ruleYMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_YPrimary_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2285:2: ( (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* ) )
            // InternalLang.g:2286:2: (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* )
            {
            // InternalLang.g:2286:2: (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* )
            // InternalLang.g:2287:3: this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYMultiplicativeExpressionAccess().getYPrimaryParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_47);
            this_YPrimary_0=ruleYPrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YPrimary_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2295:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( ((LA50_0>=44 && LA50_0<=45)) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalLang.g:2296:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) )
            	    {
            	    // InternalLang.g:2296:4: ()
            	    // InternalLang.g:2297:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYMultiplicativeExpressionAccess().getYMulOrDivLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2303:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalLang.g:2304:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalLang.g:2304:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalLang.g:2305:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalLang.g:2305:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( (LA49_0==44) ) {
            	        alt49=1;
            	    }
            	    else if ( (LA49_0==45) ) {
            	        alt49=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 49, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt49) {
            	        case 1 :
            	            // InternalLang.g:2306:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,44,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_1, grammarAccess.getYMultiplicativeExpressionAccess().getOpAsteriskKeyword_1_1_0_0());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getYMultiplicativeExpressionRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              						
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalLang.g:2317:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,45,FOLLOW_32); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							newLeafNode(lv_op_2_2, grammarAccess.getYMultiplicativeExpressionAccess().getOpSolidusKeyword_1_1_0_1());
            	              						
            	            }
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElement(grammarAccess.getYMultiplicativeExpressionRule());
            	              							}
            	              							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              						
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalLang.g:2330:4: ( (lv_right_3_0= ruleYPrimary ) )
            	    // InternalLang.g:2331:5: (lv_right_3_0= ruleYPrimary )
            	    {
            	    // InternalLang.g:2331:5: (lv_right_3_0= ruleYPrimary )
            	    // InternalLang.g:2332:6: lv_right_3_0= ruleYPrimary
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYMultiplicativeExpressionAccess().getRightYPrimaryParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_47);
            	    lv_right_3_0=ruleYPrimary();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYMultiplicativeExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YPrimary");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYMultiplicativeExpression"


    // $ANTLR start "entryRuleYPrimary"
    // InternalLang.g:2354:1: entryRuleYPrimary returns [EObject current=null] : iv_ruleYPrimary= ruleYPrimary EOF ;
    public final EObject entryRuleYPrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYPrimary = null;


        try {
            // InternalLang.g:2354:49: (iv_ruleYPrimary= ruleYPrimary EOF )
            // InternalLang.g:2355:2: iv_ruleYPrimary= ruleYPrimary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYPrimaryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYPrimary=ruleYPrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYPrimary; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYPrimary"


    // $ANTLR start "ruleYPrimary"
    // InternalLang.g:2361:1: ruleYPrimary returns [EObject current=null] : ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression ) ;
    public final EObject ruleYPrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_a_2_0 = null;

        EObject lv_expression_6_0 = null;

        EObject this_YSelectionExpression_7 = null;



        	enterRule();

        try {
            // InternalLang.g:2367:2: ( ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression ) )
            // InternalLang.g:2368:2: ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression )
            {
            // InternalLang.g:2368:2: ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression )
            int alt51=3;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt51=1;
                }
                break;
            case 46:
                {
                alt51=2;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_STRING:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt51=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // InternalLang.g:2369:3: ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' )
                    {
                    // InternalLang.g:2369:3: ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' )
                    // InternalLang.g:2370:4: () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')'
                    {
                    // InternalLang.g:2370:4: ()
                    // InternalLang.g:2371:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYPrimaryAccess().getYParentiesAction_0_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_1=(Token)match(input,27,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getYPrimaryAccess().getLeftParenthesisKeyword_0_1());
                      			
                    }
                    // InternalLang.g:2381:4: ( (lv_a_2_0= ruleYOrExpression ) )
                    // InternalLang.g:2382:5: (lv_a_2_0= ruleYOrExpression )
                    {
                    // InternalLang.g:2382:5: (lv_a_2_0= ruleYOrExpression )
                    // InternalLang.g:2383:6: lv_a_2_0= ruleYOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYPrimaryAccess().getAYOrExpressionParserRuleCall_0_2_0());
                      					
                    }
                    pushFollow(FOLLOW_34);
                    lv_a_2_0=ruleYOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYPrimaryRule());
                      						}
                      						set(
                      							current,
                      							"a",
                      							lv_a_2_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getYPrimaryAccess().getRightParenthesisKeyword_0_3());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLang.g:2406:3: ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) )
                    {
                    // InternalLang.g:2406:3: ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) )
                    // InternalLang.g:2407:4: () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) )
                    {
                    // InternalLang.g:2407:4: ()
                    // InternalLang.g:2408:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYPrimaryAccess().getYNotAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_5=(Token)match(input,46,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYPrimaryAccess().getExclamationMarkKeyword_1_1());
                      			
                    }
                    // InternalLang.g:2418:4: ( (lv_expression_6_0= ruleYPrimary ) )
                    // InternalLang.g:2419:5: (lv_expression_6_0= ruleYPrimary )
                    {
                    // InternalLang.g:2419:5: (lv_expression_6_0= ruleYPrimary )
                    // InternalLang.g:2420:6: lv_expression_6_0= ruleYPrimary
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYPrimaryAccess().getExpressionYPrimaryParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_6_0=ruleYPrimary();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYPrimaryRule());
                      						}
                      						set(
                      							current,
                      							"expression",
                      							lv_expression_6_0,
                      							"eu.jgen.notes.dmw.lite.Lang.YPrimary");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLang.g:2439:3: this_YSelectionExpression_7= ruleYSelectionExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYPrimaryAccess().getYSelectionExpressionParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YSelectionExpression_7=ruleYSelectionExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YSelectionExpression_7;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYPrimary"


    // $ANTLR start "entryRuleYTerminalExpression"
    // InternalLang.g:2451:1: entryRuleYTerminalExpression returns [EObject current=null] : iv_ruleYTerminalExpression= ruleYTerminalExpression EOF ;
    public final EObject entryRuleYTerminalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYTerminalExpression = null;


        try {
            // InternalLang.g:2451:60: (iv_ruleYTerminalExpression= ruleYTerminalExpression EOF )
            // InternalLang.g:2452:2: iv_ruleYTerminalExpression= ruleYTerminalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYTerminalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYTerminalExpression=ruleYTerminalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYTerminalExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYTerminalExpression"


    // $ANTLR start "ruleYTerminalExpression"
    // InternalLang.g:2458:1: ruleYTerminalExpression returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) ) ;
    public final EObject ruleYTerminalExpression() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token lv_value_3_0=null;
        Token lv_value_5_1=null;
        Token lv_value_5_2=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        EObject lv_arguments_18_0 = null;

        EObject lv_arguments_20_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2464:2: ( ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) ) )
            // InternalLang.g:2465:2: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) )
            {
            // InternalLang.g:2465:2: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) )
            int alt55=8;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt55=1;
                }
                break;
            case RULE_INT:
                {
                alt55=2;
                }
                break;
            case 47:
            case 48:
                {
                alt55=3;
                }
                break;
            case 49:
                {
                alt55=4;
                }
                break;
            case 50:
                {
                alt55=5;
                }
                break;
            case 51:
                {
                alt55=6;
                }
                break;
            case RULE_ID:
                {
                alt55=7;
                }
                break;
            case 52:
                {
                alt55=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // InternalLang.g:2466:3: ( () ( (lv_value_1_0= RULE_STRING ) ) )
                    {
                    // InternalLang.g:2466:3: ( () ( (lv_value_1_0= RULE_STRING ) ) )
                    // InternalLang.g:2467:4: () ( (lv_value_1_0= RULE_STRING ) )
                    {
                    // InternalLang.g:2467:4: ()
                    // InternalLang.g:2468:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYStringConstantAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2474:4: ( (lv_value_1_0= RULE_STRING ) )
                    // InternalLang.g:2475:5: (lv_value_1_0= RULE_STRING )
                    {
                    // InternalLang.g:2475:5: (lv_value_1_0= RULE_STRING )
                    // InternalLang.g:2476:6: lv_value_1_0= RULE_STRING
                    {
                    lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_value_1_0, grammarAccess.getYTerminalExpressionAccess().getValueSTRINGTerminalRuleCall_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"value",
                      							lv_value_1_0,
                      							"org.eclipse.xtext.common.Terminals.STRING");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalLang.g:2494:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalLang.g:2494:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalLang.g:2495:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalLang.g:2495:4: ()
                    // InternalLang.g:2496:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYIntConstantAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2502:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalLang.g:2503:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalLang.g:2503:5: (lv_value_3_0= RULE_INT )
                    // InternalLang.g:2504:6: lv_value_3_0= RULE_INT
                    {
                    lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_value_3_0, grammarAccess.getYTerminalExpressionAccess().getValueINTTerminalRuleCall_1_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"value",
                      							lv_value_3_0,
                      							"org.eclipse.xtext.common.Terminals.INT");
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalLang.g:2522:3: ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) )
                    {
                    // InternalLang.g:2522:3: ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) )
                    // InternalLang.g:2523:4: () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) )
                    {
                    // InternalLang.g:2523:4: ()
                    // InternalLang.g:2524:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYBoolConstantAction_2_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2530:4: ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) )
                    // InternalLang.g:2531:5: ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) )
                    {
                    // InternalLang.g:2531:5: ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) )
                    // InternalLang.g:2532:6: (lv_value_5_1= 'true' | lv_value_5_2= 'false' )
                    {
                    // InternalLang.g:2532:6: (lv_value_5_1= 'true' | lv_value_5_2= 'false' )
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==47) ) {
                        alt52=1;
                    }
                    else if ( (LA52_0==48) ) {
                        alt52=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 52, 0, input);

                        throw nvae;
                    }
                    switch (alt52) {
                        case 1 :
                            // InternalLang.g:2533:7: lv_value_5_1= 'true'
                            {
                            lv_value_5_1=(Token)match(input,47,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(lv_value_5_1, grammarAccess.getYTerminalExpressionAccess().getValueTrueKeyword_2_1_0_0());
                              						
                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                              							}
                              							setWithLastConsumed(current, "value", lv_value_5_1, null);
                              						
                            }

                            }
                            break;
                        case 2 :
                            // InternalLang.g:2544:7: lv_value_5_2= 'false'
                            {
                            lv_value_5_2=(Token)match(input,48,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(lv_value_5_2, grammarAccess.getYTerminalExpressionAccess().getValueFalseKeyword_2_1_0_1());
                              						
                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                              							}
                              							setWithLastConsumed(current, "value", lv_value_5_2, null);
                              						
                            }

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalLang.g:2559:3: ( () otherlv_7= 'self' )
                    {
                    // InternalLang.g:2559:3: ( () otherlv_7= 'self' )
                    // InternalLang.g:2560:4: () otherlv_7= 'self'
                    {
                    // InternalLang.g:2560:4: ()
                    // InternalLang.g:2561:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYSelfAction_3_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_7=(Token)match(input,49,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getYTerminalExpressionAccess().getSelfKeyword_3_1());
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalLang.g:2573:3: ( () otherlv_9= 'super' )
                    {
                    // InternalLang.g:2573:3: ( () otherlv_9= 'super' )
                    // InternalLang.g:2574:4: () otherlv_9= 'super'
                    {
                    // InternalLang.g:2574:4: ()
                    // InternalLang.g:2575:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYSuperAction_4_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_9=(Token)match(input,50,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_9, grammarAccess.getYTerminalExpressionAccess().getSuperKeyword_4_1());
                      			
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalLang.g:2587:3: ( () otherlv_11= 'null' )
                    {
                    // InternalLang.g:2587:3: ( () otherlv_11= 'null' )
                    // InternalLang.g:2588:4: () otherlv_11= 'null'
                    {
                    // InternalLang.g:2588:4: ()
                    // InternalLang.g:2589:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYNullAction_5_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_11=(Token)match(input,51,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_11, grammarAccess.getYTerminalExpressionAccess().getNullKeyword_5_1());
                      			
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalLang.g:2601:3: ( () ( (otherlv_13= RULE_ID ) ) )
                    {
                    // InternalLang.g:2601:3: ( () ( (otherlv_13= RULE_ID ) ) )
                    // InternalLang.g:2602:4: () ( (otherlv_13= RULE_ID ) )
                    {
                    // InternalLang.g:2602:4: ()
                    // InternalLang.g:2603:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYSymbolRefAction_6_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2609:4: ( (otherlv_13= RULE_ID ) )
                    // InternalLang.g:2610:5: (otherlv_13= RULE_ID )
                    {
                    // InternalLang.g:2610:5: (otherlv_13= RULE_ID )
                    // InternalLang.g:2611:6: otherlv_13= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                      						}
                      					
                    }
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_13, grammarAccess.getYTerminalExpressionAccess().getSymbolYSymbolCrossReference_6_1_0());
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalLang.g:2624:3: ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' )
                    {
                    // InternalLang.g:2624:3: ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' )
                    // InternalLang.g:2625:4: () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')'
                    {
                    // InternalLang.g:2625:4: ()
                    // InternalLang.g:2626:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYNewAction_7_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_15=(Token)match(input,52,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_15, grammarAccess.getYTerminalExpressionAccess().getNewKeyword_7_1());
                      			
                    }
                    // InternalLang.g:2636:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:2637:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:2637:5: ( ruleQualifiedName )
                    // InternalLang.g:2638:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYTerminalExpressionRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYTerminalExpressionAccess().getTypeYClassCrossReference_7_2_0());
                      					
                    }
                    pushFollow(FOLLOW_25);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_17=(Token)match(input,27,FOLLOW_41); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_17, grammarAccess.getYTerminalExpressionAccess().getLeftParenthesisKeyword_7_3());
                      			
                    }
                    // InternalLang.g:2656:4: ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( ((LA54_0>=RULE_ID && LA54_0<=RULE_STRING)||LA54_0==27||(LA54_0>=46 && LA54_0<=52)) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // InternalLang.g:2657:5: ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )*
                            {
                            // InternalLang.g:2657:5: ( (lv_arguments_18_0= ruleYOrExpression ) )
                            // InternalLang.g:2658:6: (lv_arguments_18_0= ruleYOrExpression )
                            {
                            // InternalLang.g:2658:6: (lv_arguments_18_0= ruleYOrExpression )
                            // InternalLang.g:2659:7: lv_arguments_18_0= ruleYOrExpression
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getYTerminalExpressionAccess().getArgumentsYOrExpressionParserRuleCall_7_4_0_0());
                              						
                            }
                            pushFollow(FOLLOW_27);
                            lv_arguments_18_0=ruleYOrExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getYTerminalExpressionRule());
                              							}
                              							add(
                              								current,
                              								"arguments",
                              								lv_arguments_18_0,
                              								"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            // InternalLang.g:2676:5: (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )*
                            loop53:
                            do {
                                int alt53=2;
                                int LA53_0 = input.LA(1);

                                if ( (LA53_0==24) ) {
                                    alt53=1;
                                }


                                switch (alt53) {
                            	case 1 :
                            	    // InternalLang.g:2677:6: otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) )
                            	    {
                            	    otherlv_19=(Token)match(input,24,FOLLOW_32); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_19, grammarAccess.getYTerminalExpressionAccess().getCommaKeyword_7_4_1_0());
                            	      					
                            	    }
                            	    // InternalLang.g:2681:6: ( (lv_arguments_20_0= ruleYOrExpression ) )
                            	    // InternalLang.g:2682:7: (lv_arguments_20_0= ruleYOrExpression )
                            	    {
                            	    // InternalLang.g:2682:7: (lv_arguments_20_0= ruleYOrExpression )
                            	    // InternalLang.g:2683:8: lv_arguments_20_0= ruleYOrExpression
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      								newCompositeNode(grammarAccess.getYTerminalExpressionAccess().getArgumentsYOrExpressionParserRuleCall_7_4_1_1_0());
                            	      							
                            	    }
                            	    pushFollow(FOLLOW_27);
                            	    lv_arguments_20_0=ruleYOrExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      								if (current==null) {
                            	      									current = createModelElementForParent(grammarAccess.getYTerminalExpressionRule());
                            	      								}
                            	      								add(
                            	      									current,
                            	      									"arguments",
                            	      									lv_arguments_20_0,
                            	      									"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
                            	      								afterParserOrEnumRuleCall();
                            	      							
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop53;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_21=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_21, grammarAccess.getYTerminalExpressionAccess().getRightParenthesisKeyword_7_5());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYTerminalExpression"


    // $ANTLR start "entryRuleYReadStatement"
    // InternalLang.g:2711:1: entryRuleYReadStatement returns [EObject current=null] : iv_ruleYReadStatement= ruleYReadStatement EOF ;
    public final EObject entryRuleYReadStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReadStatement = null;


        try {
            // InternalLang.g:2711:55: (iv_ruleYReadStatement= ruleYReadStatement EOF )
            // InternalLang.g:2712:2: iv_ruleYReadStatement= ruleYReadStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYReadStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYReadStatement=ruleYReadStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYReadStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYReadStatement"


    // $ANTLR start "ruleYReadStatement"
    // InternalLang.g:2718:1: ruleYReadStatement returns [EObject current=null] : (otherlv_0= 'db-read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) ) ;
    public final EObject ruleYReadStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_structs_1_0 = null;

        EObject lv_structs_3_0 = null;

        EObject lv_joinclause_4_0 = null;

        EObject lv_whereclause_5_0 = null;

        EObject lv_success_7_0 = null;

        EObject lv_notfound_10_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2724:2: ( (otherlv_0= 'db-read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) ) )
            // InternalLang.g:2725:2: (otherlv_0= 'db-read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) )
            {
            // InternalLang.g:2725:2: (otherlv_0= 'db-read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) )
            // InternalLang.g:2726:3: otherlv_0= 'db-read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYReadStatementAccess().getDbReadKeyword_0());
              		
            }
            // InternalLang.g:2730:3: ( (lv_structs_1_0= ruleYStructRefPair ) )
            // InternalLang.g:2731:4: (lv_structs_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:2731:4: (lv_structs_1_0= ruleYStructRefPair )
            // InternalLang.g:2732:5: lv_structs_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadStatementAccess().getStructsYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_48);
            lv_structs_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadStatementRule());
              					}
              					add(
              						current,
              						"structs",
              						lv_structs_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:2749:3: (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==24) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalLang.g:2750:4: otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    {
            	    otherlv_2=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getYReadStatementAccess().getCommaKeyword_2_0());
            	      			
            	    }
            	    // InternalLang.g:2754:4: ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    // InternalLang.g:2755:5: (lv_structs_3_0= ruleYStructRefPair )
            	    {
            	    // InternalLang.g:2755:5: (lv_structs_3_0= ruleYStructRefPair )
            	    // InternalLang.g:2756:6: lv_structs_3_0= ruleYStructRefPair
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYReadStatementAccess().getStructsYStructRefPairParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_48);
            	    lv_structs_3_0=ruleYStructRefPair();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYReadStatementRule());
            	      						}
            	      						add(
            	      							current,
            	      							"structs",
            	      							lv_structs_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);

            // InternalLang.g:2774:3: ( (lv_joinclause_4_0= ruleYJoin ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==68) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalLang.g:2775:4: (lv_joinclause_4_0= ruleYJoin )
                    {
                    // InternalLang.g:2775:4: (lv_joinclause_4_0= ruleYJoin )
                    // InternalLang.g:2776:5: lv_joinclause_4_0= ruleYJoin
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadStatementAccess().getJoinclauseYJoinParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_49);
                    lv_joinclause_4_0=ruleYJoin();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadStatementRule());
                      					}
                      					set(
                      						current,
                      						"joinclause",
                      						lv_joinclause_4_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YJoin");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:2793:3: ( (lv_whereclause_5_0= ruleYWhere ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==69) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalLang.g:2794:4: (lv_whereclause_5_0= ruleYWhere )
                    {
                    // InternalLang.g:2794:4: (lv_whereclause_5_0= ruleYWhere )
                    // InternalLang.g:2795:5: lv_whereclause_5_0= ruleYWhere
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadStatementAccess().getWhereclauseYWhereParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_50);
                    lv_whereclause_5_0=ruleYWhere();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadStatementRule());
                      					}
                      					set(
                      						current,
                      						"whereclause",
                      						lv_whereclause_5_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YWhere");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,54,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYReadStatementAccess().getSuccessKeyword_5());
              		
            }
            // InternalLang.g:2816:3: ( (lv_success_7_0= ruleYBlock ) )
            // InternalLang.g:2817:4: (lv_success_7_0= ruleYBlock )
            {
            // InternalLang.g:2817:4: (lv_success_7_0= ruleYBlock )
            // InternalLang.g:2818:5: lv_success_7_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadStatementAccess().getSuccessYBlockParserRuleCall_6_0());
              				
            }
            pushFollow(FOLLOW_51);
            lv_success_7_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadStatementRule());
              					}
              					set(
              						current,
              						"success",
              						lv_success_7_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_8=(Token)match(input,55,FOLLOW_52); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getYReadStatementAccess().getNotKeyword_7());
              		
            }
            otherlv_9=(Token)match(input,56,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getYReadStatementAccess().getFoundKeyword_8());
              		
            }
            // InternalLang.g:2843:3: ( (lv_notfound_10_0= ruleYBlock ) )
            // InternalLang.g:2844:4: (lv_notfound_10_0= ruleYBlock )
            {
            // InternalLang.g:2844:4: (lv_notfound_10_0= ruleYBlock )
            // InternalLang.g:2845:5: lv_notfound_10_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadStatementAccess().getNotfoundYBlockParserRuleCall_9_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_notfound_10_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadStatementRule());
              					}
              					set(
              						current,
              						"notfound",
              						lv_notfound_10_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYReadStatement"


    // $ANTLR start "entryRuleYReadEachStatement"
    // InternalLang.g:2866:1: entryRuleYReadEachStatement returns [EObject current=null] : iv_ruleYReadEachStatement= ruleYReadEachStatement EOF ;
    public final EObject entryRuleYReadEachStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReadEachStatement = null;


        try {
            // InternalLang.g:2866:59: (iv_ruleYReadEachStatement= ruleYReadEachStatement EOF )
            // InternalLang.g:2867:2: iv_ruleYReadEachStatement= ruleYReadEachStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYReadEachStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYReadEachStatement=ruleYReadEachStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYReadEachStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYReadEachStatement"


    // $ANTLR start "ruleYReadEachStatement"
    // InternalLang.g:2873:1: ruleYReadEachStatement returns [EObject current=null] : (otherlv_0= 'db-read-each' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'target' ( (otherlv_7= RULE_ID ) ) ( (lv_success_8_0= ruleYBlock ) ) ) ;
    public final EObject ruleYReadEachStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_structs_1_0 = null;

        EObject lv_structs_3_0 = null;

        EObject lv_joinclause_4_0 = null;

        EObject lv_whereclause_5_0 = null;

        EObject lv_success_8_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2879:2: ( (otherlv_0= 'db-read-each' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'target' ( (otherlv_7= RULE_ID ) ) ( (lv_success_8_0= ruleYBlock ) ) ) )
            // InternalLang.g:2880:2: (otherlv_0= 'db-read-each' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'target' ( (otherlv_7= RULE_ID ) ) ( (lv_success_8_0= ruleYBlock ) ) )
            {
            // InternalLang.g:2880:2: (otherlv_0= 'db-read-each' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'target' ( (otherlv_7= RULE_ID ) ) ( (lv_success_8_0= ruleYBlock ) ) )
            // InternalLang.g:2881:3: otherlv_0= 'db-read-each' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'target' ( (otherlv_7= RULE_ID ) ) ( (lv_success_8_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,57,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYReadEachStatementAccess().getDbReadEachKeyword_0());
              		
            }
            // InternalLang.g:2885:3: ( (lv_structs_1_0= ruleYStructRefPair ) )
            // InternalLang.g:2886:4: (lv_structs_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:2886:4: (lv_structs_1_0= ruleYStructRefPair )
            // InternalLang.g:2887:5: lv_structs_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getStructsYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_53);
            lv_structs_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
              					}
              					add(
              						current,
              						"structs",
              						lv_structs_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:2904:3: (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==24) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalLang.g:2905:4: otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    {
            	    otherlv_2=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getYReadEachStatementAccess().getCommaKeyword_2_0());
            	      			
            	    }
            	    // InternalLang.g:2909:4: ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    // InternalLang.g:2910:5: (lv_structs_3_0= ruleYStructRefPair )
            	    {
            	    // InternalLang.g:2910:5: (lv_structs_3_0= ruleYStructRefPair )
            	    // InternalLang.g:2911:6: lv_structs_3_0= ruleYStructRefPair
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYReadEachStatementAccess().getStructsYStructRefPairParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_53);
            	    lv_structs_3_0=ruleYStructRefPair();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
            	      						}
            	      						add(
            	      							current,
            	      							"structs",
            	      							lv_structs_3_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

            // InternalLang.g:2929:3: ( (lv_joinclause_4_0= ruleYJoin ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==68) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalLang.g:2930:4: (lv_joinclause_4_0= ruleYJoin )
                    {
                    // InternalLang.g:2930:4: (lv_joinclause_4_0= ruleYJoin )
                    // InternalLang.g:2931:5: lv_joinclause_4_0= ruleYJoin
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getJoinclauseYJoinParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_54);
                    lv_joinclause_4_0=ruleYJoin();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
                      					}
                      					set(
                      						current,
                      						"joinclause",
                      						lv_joinclause_4_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YJoin");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:2948:3: ( (lv_whereclause_5_0= ruleYWhere ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==69) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalLang.g:2949:4: (lv_whereclause_5_0= ruleYWhere )
                    {
                    // InternalLang.g:2949:4: (lv_whereclause_5_0= ruleYWhere )
                    // InternalLang.g:2950:5: lv_whereclause_5_0= ruleYWhere
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getWhereclauseYWhereParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_55);
                    lv_whereclause_5_0=ruleYWhere();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
                      					}
                      					set(
                      						current,
                      						"whereclause",
                      						lv_whereclause_5_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YWhere");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,58,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYReadEachStatementAccess().getTargetKeyword_5());
              		
            }
            // InternalLang.g:2971:3: ( (otherlv_7= RULE_ID ) )
            // InternalLang.g:2972:4: (otherlv_7= RULE_ID )
            {
            // InternalLang.g:2972:4: (otherlv_7= RULE_ID )
            // InternalLang.g:2973:5: otherlv_7= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYReadEachStatementRule());
              					}
              				
            }
            otherlv_7=(Token)match(input,RULE_ID,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_7, grammarAccess.getYReadEachStatementAccess().getTargetYPropertyCrossReference_6_0());
              				
            }

            }


            }

            // InternalLang.g:2984:3: ( (lv_success_8_0= ruleYBlock ) )
            // InternalLang.g:2985:4: (lv_success_8_0= ruleYBlock )
            {
            // InternalLang.g:2985:4: (lv_success_8_0= ruleYBlock )
            // InternalLang.g:2986:5: lv_success_8_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getSuccessYBlockParserRuleCall_7_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_success_8_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
              					}
              					set(
              						current,
              						"success",
              						lv_success_8_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYReadEachStatement"


    // $ANTLR start "entryRuleYCreateStatement"
    // InternalLang.g:3007:1: entryRuleYCreateStatement returns [EObject current=null] : iv_ruleYCreateStatement= ruleYCreateStatement EOF ;
    public final EObject entryRuleYCreateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYCreateStatement = null;


        try {
            // InternalLang.g:3007:57: (iv_ruleYCreateStatement= ruleYCreateStatement EOF )
            // InternalLang.g:3008:2: iv_ruleYCreateStatement= ruleYCreateStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYCreateStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYCreateStatement=ruleYCreateStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYCreateStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYCreateStatement"


    // $ANTLR start "ruleYCreateStatement"
    // InternalLang.g:3014:1: ruleYCreateStatement returns [EObject current=null] : (otherlv_0= 'db-create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) ) ;
    public final EObject ruleYCreateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_struct_1_0 = null;

        EObject lv_setBlock_2_0 = null;

        EObject lv_success_4_0 = null;

        EObject lv_alreadyExist_7_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3020:2: ( (otherlv_0= 'db-create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) ) )
            // InternalLang.g:3021:2: (otherlv_0= 'db-create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3021:2: (otherlv_0= 'db-create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) )
            // InternalLang.g:3022:3: otherlv_0= 'db-create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,59,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYCreateStatementAccess().getDbCreateKeyword_0());
              		
            }
            // InternalLang.g:3026:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3027:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3027:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3028:5: lv_struct_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYCreateStatementAccess().getStructYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_28);
            lv_struct_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYCreateStatementRule());
              					}
              					set(
              						current,
              						"struct",
              						lv_struct_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:3045:3: ( (lv_setBlock_2_0= ruleYBlock ) )
            // InternalLang.g:3046:4: (lv_setBlock_2_0= ruleYBlock )
            {
            // InternalLang.g:3046:4: (lv_setBlock_2_0= ruleYBlock )
            // InternalLang.g:3047:5: lv_setBlock_2_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYCreateStatementAccess().getSetBlockYBlockParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_50);
            lv_setBlock_2_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYCreateStatementRule());
              					}
              					set(
              						current,
              						"setBlock",
              						lv_setBlock_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,54,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYCreateStatementAccess().getSuccessKeyword_3());
              		
            }
            // InternalLang.g:3068:3: ( (lv_success_4_0= ruleYBlock ) )
            // InternalLang.g:3069:4: (lv_success_4_0= ruleYBlock )
            {
            // InternalLang.g:3069:4: (lv_success_4_0= ruleYBlock )
            // InternalLang.g:3070:5: lv_success_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYCreateStatementAccess().getSuccessYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_56);
            lv_success_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYCreateStatementRule());
              					}
              					set(
              						current,
              						"success",
              						lv_success_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_5=(Token)match(input,60,FOLLOW_57); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYCreateStatementAccess().getAlreadyKeyword_5());
              		
            }
            otherlv_6=(Token)match(input,61,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYCreateStatementAccess().getExistKeyword_6());
              		
            }
            // InternalLang.g:3095:3: ( (lv_alreadyExist_7_0= ruleYBlock ) )
            // InternalLang.g:3096:4: (lv_alreadyExist_7_0= ruleYBlock )
            {
            // InternalLang.g:3096:4: (lv_alreadyExist_7_0= ruleYBlock )
            // InternalLang.g:3097:5: lv_alreadyExist_7_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYCreateStatementAccess().getAlreadyExistYBlockParserRuleCall_7_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_alreadyExist_7_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYCreateStatementRule());
              					}
              					set(
              						current,
              						"alreadyExist",
              						lv_alreadyExist_7_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYCreateStatement"


    // $ANTLR start "entryRuleYUpdateStatement"
    // InternalLang.g:3118:1: entryRuleYUpdateStatement returns [EObject current=null] : iv_ruleYUpdateStatement= ruleYUpdateStatement EOF ;
    public final EObject entryRuleYUpdateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYUpdateStatement = null;


        try {
            // InternalLang.g:3118:57: (iv_ruleYUpdateStatement= ruleYUpdateStatement EOF )
            // InternalLang.g:3119:2: iv_ruleYUpdateStatement= ruleYUpdateStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYUpdateStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYUpdateStatement=ruleYUpdateStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYUpdateStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYUpdateStatement"


    // $ANTLR start "ruleYUpdateStatement"
    // InternalLang.g:3125:1: ruleYUpdateStatement returns [EObject current=null] : (otherlv_0= 'db-update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYUpdateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_struct_1_0 = null;

        EObject lv_setBlock_2_0 = null;

        EObject lv_success_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3131:2: ( (otherlv_0= 'db-update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3132:2: (otherlv_0= 'db-update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3132:2: (otherlv_0= 'db-update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) )
            // InternalLang.g:3133:3: otherlv_0= 'db-update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,62,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYUpdateStatementAccess().getDbUpdateKeyword_0());
              		
            }
            // InternalLang.g:3137:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3138:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3138:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3139:5: lv_struct_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYUpdateStatementAccess().getStructYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_28);
            lv_struct_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYUpdateStatementRule());
              					}
              					set(
              						current,
              						"struct",
              						lv_struct_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:3156:3: ( (lv_setBlock_2_0= ruleYBlock ) )
            // InternalLang.g:3157:4: (lv_setBlock_2_0= ruleYBlock )
            {
            // InternalLang.g:3157:4: (lv_setBlock_2_0= ruleYBlock )
            // InternalLang.g:3158:5: lv_setBlock_2_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYUpdateStatementAccess().getSetBlockYBlockParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_50);
            lv_setBlock_2_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYUpdateStatementRule());
              					}
              					set(
              						current,
              						"setBlock",
              						lv_setBlock_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,54,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYUpdateStatementAccess().getSuccessKeyword_3());
              		
            }
            // InternalLang.g:3179:3: ( (lv_success_4_0= ruleYBlock ) )
            // InternalLang.g:3180:4: (lv_success_4_0= ruleYBlock )
            {
            // InternalLang.g:3180:4: (lv_success_4_0= ruleYBlock )
            // InternalLang.g:3181:5: lv_success_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYUpdateStatementAccess().getSuccessYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_success_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYUpdateStatementRule());
              					}
              					set(
              						current,
              						"success",
              						lv_success_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYUpdateStatement"


    // $ANTLR start "entryRuleYDeleteStatement"
    // InternalLang.g:3202:1: entryRuleYDeleteStatement returns [EObject current=null] : iv_ruleYDeleteStatement= ruleYDeleteStatement EOF ;
    public final EObject entryRuleYDeleteStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYDeleteStatement = null;


        try {
            // InternalLang.g:3202:57: (iv_ruleYDeleteStatement= ruleYDeleteStatement EOF )
            // InternalLang.g:3203:2: iv_ruleYDeleteStatement= ruleYDeleteStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYDeleteStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYDeleteStatement=ruleYDeleteStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYDeleteStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYDeleteStatement"


    // $ANTLR start "ruleYDeleteStatement"
    // InternalLang.g:3209:1: ruleYDeleteStatement returns [EObject current=null] : (otherlv_0= 'db-delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' ) ;
    public final EObject ruleYDeleteStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_struct_1_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3215:2: ( (otherlv_0= 'db-delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' ) )
            // InternalLang.g:3216:2: (otherlv_0= 'db-delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' )
            {
            // InternalLang.g:3216:2: (otherlv_0= 'db-delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' )
            // InternalLang.g:3217:3: otherlv_0= 'db-delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,63,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYDeleteStatementAccess().getDbDeleteKeyword_0());
              		
            }
            // InternalLang.g:3221:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3222:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3222:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3223:5: lv_struct_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYDeleteStatementAccess().getStructYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_struct_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYDeleteStatementRule());
              					}
              					set(
              						current,
              						"struct",
              						lv_struct_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYDeleteStatementAccess().getSemicolonKeyword_2());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYDeleteStatement"


    // $ANTLR start "entryRuleYAssociateStatement"
    // InternalLang.g:3248:1: entryRuleYAssociateStatement returns [EObject current=null] : iv_ruleYAssociateStatement= ruleYAssociateStatement EOF ;
    public final EObject entryRuleYAssociateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAssociateStatement = null;


        try {
            // InternalLang.g:3248:60: (iv_ruleYAssociateStatement= ruleYAssociateStatement EOF )
            // InternalLang.g:3249:2: iv_ruleYAssociateStatement= ruleYAssociateStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAssociateStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAssociateStatement=ruleYAssociateStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAssociateStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAssociateStatement"


    // $ANTLR start "ruleYAssociateStatement"
    // InternalLang.g:3255:1: ruleYAssociateStatement returns [EObject current=null] : (otherlv_0= 'db-associate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'with' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' ) ;
    public final EObject ruleYAssociateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_struct_1_0 = null;

        EObject lv_joinref_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3261:2: ( (otherlv_0= 'db-associate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'with' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' ) )
            // InternalLang.g:3262:2: (otherlv_0= 'db-associate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'with' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' )
            {
            // InternalLang.g:3262:2: (otherlv_0= 'db-associate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'with' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' )
            // InternalLang.g:3263:3: otherlv_0= 'db-associate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'with' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAssociateStatementAccess().getDbAssociateKeyword_0());
              		
            }
            // InternalLang.g:3267:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3268:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3268:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3269:5: lv_struct_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAssociateStatementAccess().getStructYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_58);
            lv_struct_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAssociateStatementRule());
              					}
              					set(
              						current,
              						"struct",
              						lv_struct_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,65,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAssociateStatementAccess().getWithKeyword_2());
              		
            }
            // InternalLang.g:3290:3: ( (lv_joinref_3_0= ruleYJoinDef ) )
            // InternalLang.g:3291:4: (lv_joinref_3_0= ruleYJoinDef )
            {
            // InternalLang.g:3291:4: (lv_joinref_3_0= ruleYJoinDef )
            // InternalLang.g:3292:5: lv_joinref_3_0= ruleYJoinDef
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAssociateStatementAccess().getJoinrefYJoinDefParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_joinref_3_0=ruleYJoinDef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAssociateStatementRule());
              					}
              					set(
              						current,
              						"joinref",
              						lv_joinref_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.YJoinDef");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAssociateStatementAccess().getSemicolonKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAssociateStatement"


    // $ANTLR start "entryRuleYDisassociateStatement"
    // InternalLang.g:3317:1: entryRuleYDisassociateStatement returns [EObject current=null] : iv_ruleYDisassociateStatement= ruleYDisassociateStatement EOF ;
    public final EObject entryRuleYDisassociateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYDisassociateStatement = null;


        try {
            // InternalLang.g:3317:63: (iv_ruleYDisassociateStatement= ruleYDisassociateStatement EOF )
            // InternalLang.g:3318:2: iv_ruleYDisassociateStatement= ruleYDisassociateStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYDisassociateStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYDisassociateStatement=ruleYDisassociateStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYDisassociateStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYDisassociateStatement"


    // $ANTLR start "ruleYDisassociateStatement"
    // InternalLang.g:3324:1: ruleYDisassociateStatement returns [EObject current=null] : (otherlv_0= 'db-disassociate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'from' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' ) ;
    public final EObject ruleYDisassociateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_struct_1_0 = null;

        EObject lv_joinref_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3330:2: ( (otherlv_0= 'db-disassociate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'from' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' ) )
            // InternalLang.g:3331:2: (otherlv_0= 'db-disassociate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'from' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' )
            {
            // InternalLang.g:3331:2: (otherlv_0= 'db-disassociate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'from' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';' )
            // InternalLang.g:3332:3: otherlv_0= 'db-disassociate' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= 'from' ( (lv_joinref_3_0= ruleYJoinDef ) ) otherlv_4= ';'
            {
            otherlv_0=(Token)match(input,66,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYDisassociateStatementAccess().getDbDisassociateKeyword_0());
              		
            }
            // InternalLang.g:3336:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3337:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3337:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3338:5: lv_struct_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYDisassociateStatementAccess().getStructYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_59);
            lv_struct_1_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYDisassociateStatementRule());
              					}
              					set(
              						current,
              						"struct",
              						lv_struct_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,67,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYDisassociateStatementAccess().getFromKeyword_2());
              		
            }
            // InternalLang.g:3359:3: ( (lv_joinref_3_0= ruleYJoinDef ) )
            // InternalLang.g:3360:4: (lv_joinref_3_0= ruleYJoinDef )
            {
            // InternalLang.g:3360:4: (lv_joinref_3_0= ruleYJoinDef )
            // InternalLang.g:3361:5: lv_joinref_3_0= ruleYJoinDef
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYDisassociateStatementAccess().getJoinrefYJoinDefParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_joinref_3_0=ruleYJoinDef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYDisassociateStatementRule());
              					}
              					set(
              						current,
              						"joinref",
              						lv_joinref_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.YJoinDef");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYDisassociateStatementAccess().getSemicolonKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYDisassociateStatement"


    // $ANTLR start "entryRuleYStructRefPair"
    // InternalLang.g:3386:1: entryRuleYStructRefPair returns [EObject current=null] : iv_ruleYStructRefPair= ruleYStructRefPair EOF ;
    public final EObject entryRuleYStructRefPair() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYStructRefPair = null;


        try {
            // InternalLang.g:3386:55: (iv_ruleYStructRefPair= ruleYStructRefPair EOF )
            // InternalLang.g:3387:2: iv_ruleYStructRefPair= ruleYStructRefPair EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYStructRefPairRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYStructRefPair=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYStructRefPair; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYStructRefPair"


    // $ANTLR start "ruleYStructRefPair"
    // InternalLang.g:3393:1: ruleYStructRefPair returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleYStructRefPair() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalLang.g:3399:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) ) )
            // InternalLang.g:3400:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalLang.g:3400:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) )
            // InternalLang.g:3401:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) )
            {
            // InternalLang.g:3401:3: ( (otherlv_0= RULE_ID ) )
            // InternalLang.g:3402:4: (otherlv_0= RULE_ID )
            {
            // InternalLang.g:3402:4: (otherlv_0= RULE_ID )
            // InternalLang.g:3403:5: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYStructRefPairRule());
              					}
              				
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_0, grammarAccess.getYStructRefPairAccess().getStructpropertyYPropertyCrossReference_0_0());
              				
            }

            }


            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYStructRefPairAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:3418:3: ( (otherlv_2= RULE_ID ) )
            // InternalLang.g:3419:4: (otherlv_2= RULE_ID )
            {
            // InternalLang.g:3419:4: (otherlv_2= RULE_ID )
            // InternalLang.g:3420:5: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYStructRefPairRule());
              					}
              				
            }
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_2, grammarAccess.getYStructRefPairAccess().getStructclassYAnnotEntityCrossReference_2_0());
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYStructRefPair"


    // $ANTLR start "entryRuleYJoin"
    // InternalLang.g:3435:1: entryRuleYJoin returns [EObject current=null] : iv_ruleYJoin= ruleYJoin EOF ;
    public final EObject entryRuleYJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYJoin = null;


        try {
            // InternalLang.g:3435:46: (iv_ruleYJoin= ruleYJoin EOF )
            // InternalLang.g:3436:2: iv_ruleYJoin= ruleYJoin EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYJoinRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYJoin=ruleYJoin();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYJoin; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYJoin"


    // $ANTLR start "ruleYJoin"
    // InternalLang.g:3442:1: ruleYJoin returns [EObject current=null] : ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* ) ;
    public final EObject ruleYJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_joindefs_2_0 = null;

        EObject lv_joindefs_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3448:2: ( ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* ) )
            // InternalLang.g:3449:2: ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* )
            {
            // InternalLang.g:3449:2: ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* )
            // InternalLang.g:3450:3: () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )*
            {
            // InternalLang.g:3450:3: ()
            // InternalLang.g:3451:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYJoinAccess().getYJoinAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,68,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYJoinAccess().getJoinKeyword_1());
              		
            }
            // InternalLang.g:3461:3: ( (lv_joindefs_2_0= ruleYJoinDef ) )
            // InternalLang.g:3462:4: (lv_joindefs_2_0= ruleYJoinDef )
            {
            // InternalLang.g:3462:4: (lv_joindefs_2_0= ruleYJoinDef )
            // InternalLang.g:3463:5: lv_joindefs_2_0= ruleYJoinDef
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYJoinAccess().getJoindefsYJoinDefParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_61);
            lv_joindefs_2_0=ruleYJoinDef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYJoinRule());
              					}
              					add(
              						current,
              						"joindefs",
              						lv_joindefs_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YJoinDef");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:3480:3: (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==24) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalLang.g:3481:4: otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) )
            	    {
            	    otherlv_3=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getYJoinAccess().getCommaKeyword_3_0());
            	      			
            	    }
            	    // InternalLang.g:3485:4: ( (lv_joindefs_4_0= ruleYJoinDef ) )
            	    // InternalLang.g:3486:5: (lv_joindefs_4_0= ruleYJoinDef )
            	    {
            	    // InternalLang.g:3486:5: (lv_joindefs_4_0= ruleYJoinDef )
            	    // InternalLang.g:3487:6: lv_joindefs_4_0= ruleYJoinDef
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYJoinAccess().getJoindefsYJoinDefParserRuleCall_3_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_61);
            	    lv_joindefs_4_0=ruleYJoinDef();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYJoinRule());
            	      						}
            	      						add(
            	      							current,
            	      							"joindefs",
            	      							lv_joindefs_4_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YJoinDef");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYJoin"


    // $ANTLR start "entryRuleYJoinDef"
    // InternalLang.g:3509:1: entryRuleYJoinDef returns [EObject current=null] : iv_ruleYJoinDef= ruleYJoinDef EOF ;
    public final EObject entryRuleYJoinDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYJoinDef = null;


        try {
            // InternalLang.g:3509:49: (iv_ruleYJoinDef= ruleYJoinDef EOF )
            // InternalLang.g:3510:2: iv_ruleYJoinDef= ruleYJoinDef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYJoinDefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYJoinDef=ruleYJoinDef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYJoinDef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYJoinDef"


    // $ANTLR start "ruleYJoinDef"
    // InternalLang.g:3516:1: ruleYJoinDef returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) ) ;
    public final EObject ruleYJoinDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:3522:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) ) )
            // InternalLang.g:3523:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) )
            {
            // InternalLang.g:3523:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) )
            // InternalLang.g:3524:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) )
            {
            // InternalLang.g:3524:3: ( (otherlv_0= RULE_ID ) )
            // InternalLang.g:3525:4: (otherlv_0= RULE_ID )
            {
            // InternalLang.g:3525:4: (otherlv_0= RULE_ID )
            // InternalLang.g:3526:5: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYJoinDefRule());
              					}
              				
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_0, grammarAccess.getYJoinDefAccess().getFromViewYPropertyCrossReference_0_0());
              				
            }

            }


            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYJoinDefAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:3541:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:3542:4: ( ruleQualifiedName )
            {
            // InternalLang.g:3542:4: ( ruleQualifiedName )
            // InternalLang.g:3543:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYJoinDefRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYJoinDefAccess().getRelRefYAnnotRelCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_60);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYJoinDefAccess().getHyphenMinusGreaterThanSignKeyword_3());
              		
            }
            // InternalLang.g:3561:3: ( (otherlv_4= RULE_ID ) )
            // InternalLang.g:3562:4: (otherlv_4= RULE_ID )
            {
            // InternalLang.g:3562:4: (otherlv_4= RULE_ID )
            // InternalLang.g:3563:5: otherlv_4= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYJoinDefRule());
              					}
              				
            }
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_4, grammarAccess.getYJoinDefAccess().getToViewYPropertyCrossReference_4_0());
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYJoinDef"


    // $ANTLR start "entryRuleYWhere"
    // InternalLang.g:3578:1: entryRuleYWhere returns [EObject current=null] : iv_ruleYWhere= ruleYWhere EOF ;
    public final EObject entryRuleYWhere() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYWhere = null;


        try {
            // InternalLang.g:3578:47: (iv_ruleYWhere= ruleYWhere EOF )
            // InternalLang.g:3579:2: iv_ruleYWhere= ruleYWhere EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYWhereRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYWhere=ruleYWhere();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYWhere; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYWhere"


    // $ANTLR start "ruleYWhere"
    // InternalLang.g:3585:1: ruleYWhere returns [EObject current=null] : ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) ) ;
    public final EObject ruleYWhere() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3591:2: ( ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) ) )
            // InternalLang.g:3592:2: ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) )
            {
            // InternalLang.g:3592:2: ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) )
            // InternalLang.g:3593:3: () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) )
            {
            // InternalLang.g:3593:3: ()
            // InternalLang.g:3594:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYWhereAccess().getYWhereAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,69,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYWhereAccess().getWhereKeyword_1());
              		
            }
            // InternalLang.g:3604:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:3605:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:3605:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:3606:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYWhereAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_2_0=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYWhereRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYWhere"


    // $ANTLR start "entryRuleYWhileStatement"
    // InternalLang.g:3627:1: entryRuleYWhileStatement returns [EObject current=null] : iv_ruleYWhileStatement= ruleYWhileStatement EOF ;
    public final EObject entryRuleYWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYWhileStatement = null;


        try {
            // InternalLang.g:3627:56: (iv_ruleYWhileStatement= ruleYWhileStatement EOF )
            // InternalLang.g:3628:2: iv_ruleYWhileStatement= ruleYWhileStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYWhileStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYWhileStatement=ruleYWhileStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYWhileStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYWhileStatement"


    // $ANTLR start "ruleYWhileStatement"
    // InternalLang.g:3634:1: ruleYWhileStatement returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;

        EObject lv_body_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3640:2: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3641:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3641:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) )
            // InternalLang.g:3642:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,70,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYWhileStatementAccess().getWhileKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYWhileStatementAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:3650:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:3651:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:3651:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:3652:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYWhileStatementAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_expression_2_0=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYWhileStatementRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYWhileStatementAccess().getRightParenthesisKeyword_3());
              		
            }
            // InternalLang.g:3673:3: ( (lv_body_4_0= ruleYBlock ) )
            // InternalLang.g:3674:4: (lv_body_4_0= ruleYBlock )
            {
            // InternalLang.g:3674:4: (lv_body_4_0= ruleYBlock )
            // InternalLang.g:3675:5: lv_body_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYWhileStatementAccess().getBodyYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_body_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYWhileStatementRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYWhileStatement"


    // $ANTLR start "entryRuleYRepeatWhileStatement"
    // InternalLang.g:3696:1: entryRuleYRepeatWhileStatement returns [EObject current=null] : iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF ;
    public final EObject entryRuleYRepeatWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYRepeatWhileStatement = null;


        try {
            // InternalLang.g:3696:62: (iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF )
            // InternalLang.g:3697:2: iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYRepeatWhileStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYRepeatWhileStatement=ruleYRepeatWhileStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYRepeatWhileStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYRepeatWhileStatement"


    // $ANTLR start "ruleYRepeatWhileStatement"
    // InternalLang.g:3703:1: ruleYRepeatWhileStatement returns [EObject current=null] : (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' ) ;
    public final EObject ruleYRepeatWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_body_1_0 = null;

        EObject lv_expression_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3709:2: ( (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' ) )
            // InternalLang.g:3710:2: (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' )
            {
            // InternalLang.g:3710:2: (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' )
            // InternalLang.g:3711:3: otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,71,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYRepeatWhileStatementAccess().getRepeatKeyword_0());
              		
            }
            // InternalLang.g:3715:3: ( (lv_body_1_0= ruleYBlock ) )
            // InternalLang.g:3716:4: (lv_body_1_0= ruleYBlock )
            {
            // InternalLang.g:3716:4: (lv_body_1_0= ruleYBlock )
            // InternalLang.g:3717:5: lv_body_1_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYRepeatWhileStatementAccess().getBodyYBlockParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_62);
            lv_body_1_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYRepeatWhileStatementRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,70,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYRepeatWhileStatementAccess().getWhileKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,27,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYRepeatWhileStatementAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalLang.g:3742:3: ( (lv_expression_4_0= ruleYOrExpression ) )
            // InternalLang.g:3743:4: (lv_expression_4_0= ruleYOrExpression )
            {
            // InternalLang.g:3743:4: (lv_expression_4_0= ruleYOrExpression )
            // InternalLang.g:3744:5: lv_expression_4_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYRepeatWhileStatementAccess().getExpressionYOrExpressionParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_expression_4_0=ruleYOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYRepeatWhileStatementRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YOrExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYRepeatWhileStatementAccess().getRightParenthesisKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYRepeatWhileStatement"


    // $ANTLR start "entryRuleYForInStatement"
    // InternalLang.g:3769:1: entryRuleYForInStatement returns [EObject current=null] : iv_ruleYForInStatement= ruleYForInStatement EOF ;
    public final EObject entryRuleYForInStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYForInStatement = null;


        try {
            // InternalLang.g:3769:56: (iv_ruleYForInStatement= ruleYForInStatement EOF )
            // InternalLang.g:3770:2: iv_ruleYForInStatement= ruleYForInStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYForInStatementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYForInStatement=ruleYForInStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYForInStatement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYForInStatement"


    // $ANTLR start "ruleYForInStatement"
    // InternalLang.g:3776:1: ruleYForInStatement returns [EObject current=null] : (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYForInStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_body_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3782:2: ( (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3783:2: (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3783:2: (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) )
            // InternalLang.g:3784:3: otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,72,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYForInStatementAccess().getForKeyword_0());
              		
            }
            // InternalLang.g:3788:3: ( (otherlv_1= RULE_ID ) )
            // InternalLang.g:3789:4: (otherlv_1= RULE_ID )
            {
            // InternalLang.g:3789:4: (otherlv_1= RULE_ID )
            // InternalLang.g:3790:5: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYForInStatementRule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_63); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getYForInStatementAccess().getItemYPropertyCrossReference_1_0());
              				
            }

            }


            }

            otherlv_2=(Token)match(input,73,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYForInStatementAccess().getInKeyword_2());
              		
            }
            // InternalLang.g:3805:3: ( (otherlv_3= RULE_ID ) )
            // InternalLang.g:3806:4: (otherlv_3= RULE_ID )
            {
            // InternalLang.g:3806:4: (otherlv_3= RULE_ID )
            // InternalLang.g:3807:5: otherlv_3= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYForInStatementRule());
              					}
              				
            }
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_3, grammarAccess.getYForInStatementAccess().getCollectionYPropertyCrossReference_3_0());
              				
            }

            }


            }

            // InternalLang.g:3818:3: ( (lv_body_4_0= ruleYBlock ) )
            // InternalLang.g:3819:4: (lv_body_4_0= ruleYBlock )
            {
            // InternalLang.g:3819:4: (lv_body_4_0= ruleYBlock )
            // InternalLang.g:3820:5: lv_body_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYForInStatementAccess().getBodyYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_body_4_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYForInStatementRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YBlock");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYForInStatement"


    // $ANTLR start "entryRuleYAnnot"
    // InternalLang.g:3841:1: entryRuleYAnnot returns [EObject current=null] : iv_ruleYAnnot= ruleYAnnot EOF ;
    public final EObject entryRuleYAnnot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnot = null;


        try {
            // InternalLang.g:3841:47: (iv_ruleYAnnot= ruleYAnnot EOF )
            // InternalLang.g:3842:2: iv_ruleYAnnot= ruleYAnnot EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnot=ruleYAnnot();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnot; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnot"


    // $ANTLR start "ruleYAnnot"
    // InternalLang.g:3848:1: ruleYAnnot returns [EObject current=null] : ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) ) ;
    public final EObject ruleYAnnot() throws RecognitionException {
        EObject current = null;

        EObject lv_type_1_1 = null;

        EObject lv_type_1_2 = null;

        EObject lv_type_1_3 = null;

        EObject lv_type_1_4 = null;

        EObject lv_type_1_5 = null;

        EObject lv_type_1_6 = null;

        EObject lv_type_1_7 = null;



        	enterRule();

        try {
            // InternalLang.g:3854:2: ( ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) ) )
            // InternalLang.g:3855:2: ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) )
            {
            // InternalLang.g:3855:2: ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) )
            // InternalLang.g:3856:3: () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) )
            {
            // InternalLang.g:3856:3: ()
            // InternalLang.g:3857:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAccess().getYAnnotAction_0(),
              					current);
              			
            }

            }

            // InternalLang.g:3863:3: ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) )
            // InternalLang.g:3864:4: ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) )
            {
            // InternalLang.g:3864:4: ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) )
            // InternalLang.g:3865:5: (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax )
            {
            // InternalLang.g:3865:5: (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax )
            int alt63=7;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt63=1;
                }
                break;
            case 76:
                {
                alt63=2;
                }
                break;
            case 78:
                {
                alt63=3;
                }
                break;
            case 79:
                {
                alt63=4;
                }
                break;
            case 80:
                {
                alt63=5;
                }
                break;
            case 77:
                {
                alt63=6;
                }
                break;
            case 75:
                {
                alt63=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalLang.g:3866:6: lv_type_1_1= ruleYAnnotLength
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotLengthParserRuleCall_1_0_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_1=ruleYAnnotLength();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_1,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotLength");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:3882:6: lv_type_1_2= ruleYAnnotDecimal
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotDecimalParserRuleCall_1_0_1());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_2=ruleYAnnotDecimal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_2,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotDecimal");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:3898:6: lv_type_1_3= ruleYAnnotAction
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotActionParserRuleCall_1_0_2());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_3=ruleYAnnotAction();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_3,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotAction");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:3914:6: lv_type_1_4= ruleYAnnotMessage
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotMessageParserRuleCall_1_0_3());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_4=ruleYAnnotMessage();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_4,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotMessage");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 5 :
                    // InternalLang.g:3930:6: lv_type_1_5= ruleYAnnotMsgType
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotMsgTypeParserRuleCall_1_0_4());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_5=ruleYAnnotMsgType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_5,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotMsgType");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 6 :
                    // InternalLang.g:3946:6: lv_type_1_6= ruleYAnnotDefault
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotDefaultParserRuleCall_1_0_5());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_6=ruleYAnnotDefault();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_6,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotDefault");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 7 :
                    // InternalLang.g:3962:6: lv_type_1_7= ruleYAnnotMax
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAccess().getTypeYAnnotMaxParserRuleCall_1_0_6());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_1_7=ruleYAnnotMax();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_1_7,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotMax");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;

            }


            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnot"


    // $ANTLR start "entryRuleYAnnotTop"
    // InternalLang.g:3984:1: entryRuleYAnnotTop returns [EObject current=null] : iv_ruleYAnnotTop= ruleYAnnotTop EOF ;
    public final EObject entryRuleYAnnotTop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTop = null;


        try {
            // InternalLang.g:3984:50: (iv_ruleYAnnotTop= ruleYAnnotTop EOF )
            // InternalLang.g:3985:2: iv_ruleYAnnotTop= ruleYAnnotTop EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotTopRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotTop=ruleYAnnotTop();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotTop; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotTop"


    // $ANTLR start "ruleYAnnotTop"
    // InternalLang.g:3991:1: ruleYAnnotTop returns [EObject current=null] : ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) ) ;
    public final EObject ruleYAnnotTop() throws RecognitionException {
        EObject current = null;

        EObject lv_type_0_1 = null;

        EObject lv_type_0_2 = null;

        EObject lv_type_0_3 = null;

        EObject lv_type_0_4 = null;

        EObject lv_type_0_5 = null;



        	enterRule();

        try {
            // InternalLang.g:3997:2: ( ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) ) )
            // InternalLang.g:3998:2: ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) )
            {
            // InternalLang.g:3998:2: ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) )
            // InternalLang.g:3999:3: ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) )
            {
            // InternalLang.g:3999:3: ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) )
            // InternalLang.g:4000:4: (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase )
            {
            // InternalLang.g:4000:4: (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase )
            int alt64=5;
            switch ( input.LA(1) ) {
            case 81:
                {
                alt64=1;
                }
                break;
            case 87:
                {
                alt64=2;
                }
                break;
            case 106:
                {
                alt64=3;
                }
                break;
            case 104:
                {
                alt64=4;
                }
                break;
            case 108:
                {
                alt64=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalLang.g:4001:5: lv_type_0_1= ruleYAnnotEntity
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTopAccess().getTypeYAnnotEntityParserRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_0_1=ruleYAnnotEntity();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTopRule());
                      					}
                      					set(
                      						current,
                      						"type",
                      						lv_type_0_1,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotEntity");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:4017:5: lv_type_0_2= ruleYAnnotTechnicalDesign
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTopAccess().getTypeYAnnotTechnicalDesignParserRuleCall_0_1());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_0_2=ruleYAnnotTechnicalDesign();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTopRule());
                      					}
                      					set(
                      						current,
                      						"type",
                      						lv_type_0_2,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotTechnicalDesign");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:4033:5: lv_type_0_3= ruleYAnnotSwift
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTopAccess().getTypeYAnnotSwiftParserRuleCall_0_2());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_0_3=ruleYAnnotSwift();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTopRule());
                      					}
                      					set(
                      						current,
                      						"type",
                      						lv_type_0_3,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotSwift");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:4049:5: lv_type_0_4= ruleYAnnotJava
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTopAccess().getTypeYAnnotJavaParserRuleCall_0_3());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_0_4=ruleYAnnotJava();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTopRule());
                      					}
                      					set(
                      						current,
                      						"type",
                      						lv_type_0_4,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotJava");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }
                    break;
                case 5 :
                    // InternalLang.g:4065:5: lv_type_0_5= ruleYAnnotDatabase
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTopAccess().getTypeYAnnotDatabaseParserRuleCall_0_4());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_0_5=ruleYAnnotDatabase();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTopRule());
                      					}
                      					set(
                      						current,
                      						"type",
                      						lv_type_0_5,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotDatabase");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }
                    break;

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotTop"


    // $ANTLR start "entryRuleYAnnotLength"
    // InternalLang.g:4086:1: entryRuleYAnnotLength returns [EObject current=null] : iv_ruleYAnnotLength= ruleYAnnotLength EOF ;
    public final EObject entryRuleYAnnotLength() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotLength = null;


        try {
            // InternalLang.g:4086:53: (iv_ruleYAnnotLength= ruleYAnnotLength EOF )
            // InternalLang.g:4087:2: iv_ruleYAnnotLength= ruleYAnnotLength EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotLengthRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotLength=ruleYAnnotLength();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotLength; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotLength"


    // $ANTLR start "ruleYAnnotLength"
    // InternalLang.g:4093:1: ruleYAnnotLength returns [EObject current=null] : ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotLength() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_length_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:4099:2: ( ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) )
            // InternalLang.g:4100:2: ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4100:2: ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            // InternalLang.g:4101:3: () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')'
            {
            // InternalLang.g:4101:3: ()
            // InternalLang.g:4102:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotLengthAccess().getYAnnotLengthAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,74,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotLengthAccess().getLengthKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotLengthAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4116:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4117:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4117:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4118:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_34); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_length_3_0, grammarAccess.getYAnnotLengthAccess().getLengthINTTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotLengthRule());
              					}
              					setWithLastConsumed(
              						current,
              						"length",
              						lv_length_3_0,
              						"org.eclipse.xtext.common.Terminals.INT");
              				
            }

            }


            }

            otherlv_4=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotLengthAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotLength"


    // $ANTLR start "entryRuleYAnnotMax"
    // InternalLang.g:4142:1: entryRuleYAnnotMax returns [EObject current=null] : iv_ruleYAnnotMax= ruleYAnnotMax EOF ;
    public final EObject entryRuleYAnnotMax() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMax = null;


        try {
            // InternalLang.g:4142:50: (iv_ruleYAnnotMax= ruleYAnnotMax EOF )
            // InternalLang.g:4143:2: iv_ruleYAnnotMax= ruleYAnnotMax EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotMaxRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotMax=ruleYAnnotMax();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotMax; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotMax"


    // $ANTLR start "ruleYAnnotMax"
    // InternalLang.g:4149:1: ruleYAnnotMax returns [EObject current=null] : ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotMax() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_length_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:4155:2: ( ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) )
            // InternalLang.g:4156:2: ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4156:2: ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            // InternalLang.g:4157:3: () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')'
            {
            // InternalLang.g:4157:3: ()
            // InternalLang.g:4158:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotMaxAccess().getYAnnotMaxAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,75,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMaxAccess().getMaxKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotMaxAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4172:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4173:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4173:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4174:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_34); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_length_3_0, grammarAccess.getYAnnotMaxAccess().getLengthINTTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotMaxRule());
              					}
              					setWithLastConsumed(
              						current,
              						"length",
              						lv_length_3_0,
              						"org.eclipse.xtext.common.Terminals.INT");
              				
            }

            }


            }

            otherlv_4=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotMaxAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotMax"


    // $ANTLR start "entryRuleYAnnotDecimal"
    // InternalLang.g:4198:1: entryRuleYAnnotDecimal returns [EObject current=null] : iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF ;
    public final EObject entryRuleYAnnotDecimal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDecimal = null;


        try {
            // InternalLang.g:4198:54: (iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF )
            // InternalLang.g:4199:2: iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDecimalRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDecimal=ruleYAnnotDecimal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDecimal; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDecimal"


    // $ANTLR start "ruleYAnnotDecimal"
    // InternalLang.g:4205:1: ruleYAnnotDecimal returns [EObject current=null] : ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
    public final EObject ruleYAnnotDecimal() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_length_3_0=null;
        Token otherlv_4=null;
        Token lv_decimal_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalLang.g:4211:2: ( ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalLang.g:4212:2: ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalLang.g:4212:2: ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalLang.g:4213:3: () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalLang.g:4213:3: ()
            // InternalLang.g:4214:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDecimalAccess().getYAnnotDecimalAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,76,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDecimalAccess().getDecimalKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotDecimalAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4228:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4229:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4229:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4230:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_65); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_length_3_0, grammarAccess.getYAnnotDecimalAccess().getLengthINTTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotDecimalRule());
              					}
              					setWithLastConsumed(
              						current,
              						"length",
              						lv_length_3_0,
              						"org.eclipse.xtext.common.Terminals.INT");
              				
            }

            }


            }

            otherlv_4=(Token)match(input,24,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotDecimalAccess().getCommaKeyword_4());
              		
            }
            // InternalLang.g:4250:3: ( (lv_decimal_5_0= RULE_INT ) )
            // InternalLang.g:4251:4: (lv_decimal_5_0= RULE_INT )
            {
            // InternalLang.g:4251:4: (lv_decimal_5_0= RULE_INT )
            // InternalLang.g:4252:5: lv_decimal_5_0= RULE_INT
            {
            lv_decimal_5_0=(Token)match(input,RULE_INT,FOLLOW_34); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_decimal_5_0, grammarAccess.getYAnnotDecimalAccess().getDecimalINTTerminalRuleCall_5_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotDecimalRule());
              					}
              					setWithLastConsumed(
              						current,
              						"decimal",
              						lv_decimal_5_0,
              						"org.eclipse.xtext.common.Terminals.INT");
              				
            }

            }


            }

            otherlv_6=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYAnnotDecimalAccess().getRightParenthesisKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDecimal"


    // $ANTLR start "entryRuleYAnnotDefault"
    // InternalLang.g:4276:1: entryRuleYAnnotDefault returns [EObject current=null] : iv_ruleYAnnotDefault= ruleYAnnotDefault EOF ;
    public final EObject entryRuleYAnnotDefault() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefault = null;


        try {
            // InternalLang.g:4276:54: (iv_ruleYAnnotDefault= ruleYAnnotDefault EOF )
            // InternalLang.g:4277:2: iv_ruleYAnnotDefault= ruleYAnnotDefault EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDefaultRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDefault=ruleYAnnotDefault();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDefault; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDefault"


    // $ANTLR start "ruleYAnnotDefault"
    // InternalLang.g:4283:1: ruleYAnnotDefault returns [EObject current=null] : ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotDefault() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4289:2: ( ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' ) )
            // InternalLang.g:4290:2: ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4290:2: ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' )
            // InternalLang.g:4291:3: () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')'
            {
            // InternalLang.g:4291:3: ()
            // InternalLang.g:4292:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDefaultAccess().getYAnnotDefaultAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,77,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDefaultAccess().getDefaultKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_66); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotDefaultAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4306:3: ( (lv_type_3_0= ruleYAnnotDefaultType ) )
            // InternalLang.g:4307:4: (lv_type_3_0= ruleYAnnotDefaultType )
            {
            // InternalLang.g:4307:4: (lv_type_3_0= ruleYAnnotDefaultType )
            // InternalLang.g:4308:5: lv_type_3_0= ruleYAnnotDefaultType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotDefaultAccess().getTypeYAnnotDefaultTypeParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_type_3_0=ruleYAnnotDefaultType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotDefaultRule());
              					}
              					set(
              						current,
              						"type",
              						lv_type_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.YAnnotDefaultType");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotDefaultAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDefault"


    // $ANTLR start "entryRuleYAnnotDefaultType"
    // InternalLang.g:4333:1: entryRuleYAnnotDefaultType returns [EObject current=null] : iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF ;
    public final EObject entryRuleYAnnotDefaultType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultType = null;


        try {
            // InternalLang.g:4333:58: (iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF )
            // InternalLang.g:4334:2: iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDefaultTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDefaultType=ruleYAnnotDefaultType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDefaultType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDefaultType"


    // $ANTLR start "ruleYAnnotDefaultType"
    // InternalLang.g:4340:1: ruleYAnnotDefaultType returns [EObject current=null] : (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber ) ;
    public final EObject ruleYAnnotDefaultType() throws RecognitionException {
        EObject current = null;

        EObject this_YAnnotDefaultText_0 = null;

        EObject this_YAnnotDefaultNumber_1 = null;



        	enterRule();

        try {
            // InternalLang.g:4346:2: ( (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber ) )
            // InternalLang.g:4347:2: (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber )
            {
            // InternalLang.g:4347:2: (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==RULE_STRING) ) {
                alt65=1;
            }
            else if ( (LA65_0==RULE_INT) ) {
                alt65=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // InternalLang.g:4348:3: this_YAnnotDefaultText_0= ruleYAnnotDefaultText
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYAnnotDefaultTypeAccess().getYAnnotDefaultTextParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAnnotDefaultText_0=ruleYAnnotDefaultText();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAnnotDefaultText_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:4357:3: this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYAnnotDefaultTypeAccess().getYAnnotDefaultNumberParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAnnotDefaultNumber_1=ruleYAnnotDefaultNumber();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAnnotDefaultNumber_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDefaultType"


    // $ANTLR start "entryRuleYAnnotDefaultText"
    // InternalLang.g:4369:1: entryRuleYAnnotDefaultText returns [EObject current=null] : iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF ;
    public final EObject entryRuleYAnnotDefaultText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultText = null;


        try {
            // InternalLang.g:4369:58: (iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF )
            // InternalLang.g:4370:2: iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDefaultTextRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDefaultText=ruleYAnnotDefaultText();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDefaultText; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDefaultText"


    // $ANTLR start "ruleYAnnotDefaultText"
    // InternalLang.g:4376:1: ruleYAnnotDefaultText returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleYAnnotDefaultText() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalLang.g:4382:2: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // InternalLang.g:4383:2: ( (lv_value_0_0= RULE_STRING ) )
            {
            // InternalLang.g:4383:2: ( (lv_value_0_0= RULE_STRING ) )
            // InternalLang.g:4384:3: (lv_value_0_0= RULE_STRING )
            {
            // InternalLang.g:4384:3: (lv_value_0_0= RULE_STRING )
            // InternalLang.g:4385:4: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_value_0_0, grammarAccess.getYAnnotDefaultTextAccess().getValueSTRINGTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getYAnnotDefaultTextRule());
              				}
              				setWithLastConsumed(
              					current,
              					"value",
              					lv_value_0_0,
              					"org.eclipse.xtext.common.Terminals.STRING");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDefaultText"


    // $ANTLR start "entryRuleYAnnotDefaultNumber"
    // InternalLang.g:4404:1: entryRuleYAnnotDefaultNumber returns [EObject current=null] : iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF ;
    public final EObject entryRuleYAnnotDefaultNumber() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultNumber = null;


        try {
            // InternalLang.g:4404:60: (iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF )
            // InternalLang.g:4405:2: iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDefaultNumberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDefaultNumber=ruleYAnnotDefaultNumber();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDefaultNumber; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDefaultNumber"


    // $ANTLR start "ruleYAnnotDefaultNumber"
    // InternalLang.g:4411:1: ruleYAnnotDefaultNumber returns [EObject current=null] : ( (lv_value_0_0= RULE_INT ) ) ;
    public final EObject ruleYAnnotDefaultNumber() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalLang.g:4417:2: ( ( (lv_value_0_0= RULE_INT ) ) )
            // InternalLang.g:4418:2: ( (lv_value_0_0= RULE_INT ) )
            {
            // InternalLang.g:4418:2: ( (lv_value_0_0= RULE_INT ) )
            // InternalLang.g:4419:3: (lv_value_0_0= RULE_INT )
            {
            // InternalLang.g:4419:3: (lv_value_0_0= RULE_INT )
            // InternalLang.g:4420:4: lv_value_0_0= RULE_INT
            {
            lv_value_0_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_value_0_0, grammarAccess.getYAnnotDefaultNumberAccess().getValueINTTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getYAnnotDefaultNumberRule());
              				}
              				setWithLastConsumed(
              					current,
              					"value",
              					lv_value_0_0,
              					"org.eclipse.xtext.common.Terminals.INT");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDefaultNumber"


    // $ANTLR start "entryRuleYAnnotAction"
    // InternalLang.g:4439:1: entryRuleYAnnotAction returns [EObject current=null] : iv_ruleYAnnotAction= ruleYAnnotAction EOF ;
    public final EObject entryRuleYAnnotAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAction = null;


        try {
            // InternalLang.g:4439:53: (iv_ruleYAnnotAction= ruleYAnnotAction EOF )
            // InternalLang.g:4440:2: iv_ruleYAnnotAction= ruleYAnnotAction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotActionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotAction=ruleYAnnotAction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotAction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotAction"


    // $ANTLR start "ruleYAnnotAction"
    // InternalLang.g:4446:1: ruleYAnnotAction returns [EObject current=null] : ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_action_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4452:2: ( ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' ) )
            // InternalLang.g:4453:2: ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4453:2: ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' )
            // InternalLang.g:4454:3: () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')'
            {
            // InternalLang.g:4454:3: ()
            // InternalLang.g:4455:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotActionAccess().getYAnnotActionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,78,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotActionAccess().getActionKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotActionAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4469:3: ( (lv_action_3_0= ruleValidID ) )
            // InternalLang.g:4470:4: (lv_action_3_0= ruleValidID )
            {
            // InternalLang.g:4470:4: (lv_action_3_0= ruleValidID )
            // InternalLang.g:4471:5: lv_action_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotActionAccess().getActionValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_action_3_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotActionRule());
              					}
              					set(
              						current,
              						"action",
              						lv_action_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotActionAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotAction"


    // $ANTLR start "entryRuleYAnnotMessage"
    // InternalLang.g:4496:1: entryRuleYAnnotMessage returns [EObject current=null] : iv_ruleYAnnotMessage= ruleYAnnotMessage EOF ;
    public final EObject entryRuleYAnnotMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMessage = null;


        try {
            // InternalLang.g:4496:54: (iv_ruleYAnnotMessage= ruleYAnnotMessage EOF )
            // InternalLang.g:4497:2: iv_ruleYAnnotMessage= ruleYAnnotMessage EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotMessageRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotMessage=ruleYAnnotMessage();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotMessage; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotMessage"


    // $ANTLR start "ruleYAnnotMessage"
    // InternalLang.g:4503:1: ruleYAnnotMessage returns [EObject current=null] : ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_msg_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:4509:2: ( ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' ) )
            // InternalLang.g:4510:2: ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4510:2: ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' )
            // InternalLang.g:4511:3: () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')'
            {
            // InternalLang.g:4511:3: ()
            // InternalLang.g:4512:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotMessageAccess().getYAnnotMessageAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,79,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMessageAccess().getMessageKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_67); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotMessageAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4526:3: ( (lv_msg_3_0= RULE_STRING ) )
            // InternalLang.g:4527:4: (lv_msg_3_0= RULE_STRING )
            {
            // InternalLang.g:4527:4: (lv_msg_3_0= RULE_STRING )
            // InternalLang.g:4528:5: lv_msg_3_0= RULE_STRING
            {
            lv_msg_3_0=(Token)match(input,RULE_STRING,FOLLOW_34); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_msg_3_0, grammarAccess.getYAnnotMessageAccess().getMsgSTRINGTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotMessageRule());
              					}
              					setWithLastConsumed(
              						current,
              						"msg",
              						lv_msg_3_0,
              						"org.eclipse.xtext.common.Terminals.STRING");
              				
            }

            }


            }

            otherlv_4=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotMessageAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotMessage"


    // $ANTLR start "entryRuleYAnnotMsgType"
    // InternalLang.g:4552:1: entryRuleYAnnotMsgType returns [EObject current=null] : iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF ;
    public final EObject entryRuleYAnnotMsgType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMsgType = null;


        try {
            // InternalLang.g:4552:54: (iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF )
            // InternalLang.g:4553:2: iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotMsgTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotMsgType=ruleYAnnotMsgType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotMsgType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotMsgType"


    // $ANTLR start "ruleYAnnotMsgType"
    // InternalLang.g:4559:1: ruleYAnnotMsgType returns [EObject current=null] : (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' ) ;
    public final EObject ruleYAnnotMsgType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_msgtype_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4565:2: ( (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' ) )
            // InternalLang.g:4566:2: (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' )
            {
            // InternalLang.g:4566:2: (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' )
            // InternalLang.g:4567:3: otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,80,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAnnotMsgTypeAccess().getMsgtypeKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMsgTypeAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:4575:3: ( (lv_msgtype_2_0= ruleValidID ) )
            // InternalLang.g:4576:4: (lv_msgtype_2_0= ruleValidID )
            {
            // InternalLang.g:4576:4: (lv_msgtype_2_0= ruleValidID )
            // InternalLang.g:4577:5: lv_msgtype_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotMsgTypeAccess().getMsgtypeValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_34);
            lv_msgtype_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotMsgTypeRule());
              					}
              					set(
              						current,
              						"msgtype",
              						lv_msgtype_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotMsgTypeAccess().getRightParenthesisKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotMsgType"


    // $ANTLR start "entryRuleYAnnotEntityInner"
    // InternalLang.g:4602:1: entryRuleYAnnotEntityInner returns [EObject current=null] : iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF ;
    public final EObject entryRuleYAnnotEntityInner() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotEntityInner = null;


        try {
            // InternalLang.g:4602:58: (iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF )
            // InternalLang.g:4603:2: iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotEntityInnerRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotEntityInner=ruleYAnnotEntityInner();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotEntityInner; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotEntityInner"


    // $ANTLR start "ruleYAnnotEntityInner"
    // InternalLang.g:4609:1: ruleYAnnotEntityInner returns [EObject current=null] : (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId ) ;
    public final EObject ruleYAnnotEntityInner() throws RecognitionException {
        EObject current = null;

        EObject this_YAnnotAttr_0 = null;

        EObject this_YAnnotRel_1 = null;

        EObject this_YAnnotId_2 = null;



        	enterRule();

        try {
            // InternalLang.g:4615:2: ( (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId ) )
            // InternalLang.g:4616:2: (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId )
            {
            // InternalLang.g:4616:2: (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId )
            int alt66=3;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt66=1;
                }
                break;
            case 83:
                {
                alt66=2;
                }
                break;
            case 86:
                {
                alt66=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // InternalLang.g:4617:3: this_YAnnotAttr_0= ruleYAnnotAttr
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYAnnotEntityInnerAccess().getYAnnotAttrParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAnnotAttr_0=ruleYAnnotAttr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAnnotAttr_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:4626:3: this_YAnnotRel_1= ruleYAnnotRel
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYAnnotEntityInnerAccess().getYAnnotRelParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAnnotRel_1=ruleYAnnotRel();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAnnotRel_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:4635:3: this_YAnnotId_2= ruleYAnnotId
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYAnnotEntityInnerAccess().getYAnnotIdParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YAnnotId_2=ruleYAnnotId();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YAnnotId_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotEntityInner"


    // $ANTLR start "entryRuleYAnnotEntity"
    // InternalLang.g:4647:1: entryRuleYAnnotEntity returns [EObject current=null] : iv_ruleYAnnotEntity= ruleYAnnotEntity EOF ;
    public final EObject entryRuleYAnnotEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotEntity = null;


        try {
            // InternalLang.g:4647:53: (iv_ruleYAnnotEntity= ruleYAnnotEntity EOF )
            // InternalLang.g:4648:2: iv_ruleYAnnotEntity= ruleYAnnotEntity EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotEntityRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotEntity=ruleYAnnotEntity();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotEntity; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotEntity"


    // $ANTLR start "ruleYAnnotEntity"
    // InternalLang.g:4654:1: ruleYAnnotEntity returns [EObject current=null] : (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' ) ;
    public final EObject ruleYAnnotEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annots_5_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4660:2: ( (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' ) )
            // InternalLang.g:4661:2: (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' )
            {
            // InternalLang.g:4661:2: (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' )
            // InternalLang.g:4662:3: otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,81,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAnnotEntityAccess().getEntityKeyword_0());
              		
            }
            // InternalLang.g:4666:3: ( (lv_name_1_0= ruleValidID ) )
            // InternalLang.g:4667:4: (lv_name_1_0= ruleValidID )
            {
            // InternalLang.g:4667:4: (lv_name_1_0= ruleValidID )
            // InternalLang.g:4668:5: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotEntityAccess().getNameValidIDParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_68);
            lv_name_1_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotEntityRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4685:3: (otherlv_2= ':' ( ( ruleQualifiedName ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==17) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalLang.g:4686:4: otherlv_2= ':' ( ( ruleQualifiedName ) )
                    {
                    otherlv_2=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAnnotEntityAccess().getColonKeyword_2_0());
                      			
                    }
                    // InternalLang.g:4690:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:4691:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:4691:5: ( ruleQualifiedName )
                    // InternalLang.g:4692:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotEntityRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotEntityAccess().getSuperannotYAnnotEntityCrossReference_2_1_0());
                      					
                    }
                    pushFollow(FOLLOW_13);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,19,FOLLOW_69); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotEntityAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:4711:3: ( (lv_annots_5_0= ruleYAnnotEntityInner ) )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( ((LA68_0>=82 && LA68_0<=83)||LA68_0==86) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // InternalLang.g:4712:4: (lv_annots_5_0= ruleYAnnotEntityInner )
            	    {
            	    // InternalLang.g:4712:4: (lv_annots_5_0= ruleYAnnotEntityInner )
            	    // InternalLang.g:4713:5: lv_annots_5_0= ruleYAnnotEntityInner
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotEntityAccess().getAnnotsYAnnotEntityInnerParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_69);
            	    lv_annots_5_0=ruleYAnnotEntityInner();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYAnnotEntityRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annots",
            	      						lv_annots_5_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnotEntityInner");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

            otherlv_6=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYAnnotEntityAccess().getRightCurlyBracketKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotEntity"


    // $ANTLR start "entryRuleYAnnotAttr"
    // InternalLang.g:4738:1: entryRuleYAnnotAttr returns [EObject current=null] : iv_ruleYAnnotAttr= ruleYAnnotAttr EOF ;
    public final EObject entryRuleYAnnotAttr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAttr = null;


        try {
            // InternalLang.g:4738:51: (iv_ruleYAnnotAttr= ruleYAnnotAttr EOF )
            // InternalLang.g:4739:2: iv_ruleYAnnotAttr= ruleYAnnotAttr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotAttrRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotAttr=ruleYAnnotAttr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotAttr; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotAttr"


    // $ANTLR start "ruleYAnnotAttr"
    // InternalLang.g:4745:1: ruleYAnnotAttr returns [EObject current=null] : ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? ) ;
    public final EObject ruleYAnnotAttr() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_optional_5_0=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_annots_6_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4751:2: ( ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? ) )
            // InternalLang.g:4752:2: ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? )
            {
            // InternalLang.g:4752:2: ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? )
            // InternalLang.g:4753:3: () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )?
            {
            // InternalLang.g:4753:3: ()
            // InternalLang.g:4754:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAttrAccess().getYAnnotAttrAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,82,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotAttrAccess().getAttrKeyword_1());
              		
            }
            // InternalLang.g:4764:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:4765:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:4765:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:4766:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotAttrAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_16);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotAttrRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotAttrAccess().getColonKeyword_3());
              		
            }
            // InternalLang.g:4787:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:4788:4: ( ruleQualifiedName )
            {
            // InternalLang.g:4788:4: ( ruleQualifiedName )
            // InternalLang.g:4789:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotAttrRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotAttrAccess().getYclassYClassCrossReference_4_0());
              				
            }
            pushFollow(FOLLOW_70);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4803:3: ( (lv_optional_5_0= '?' ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==22) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalLang.g:4804:4: (lv_optional_5_0= '?' )
                    {
                    // InternalLang.g:4804:4: (lv_optional_5_0= '?' )
                    // InternalLang.g:4805:5: lv_optional_5_0= '?'
                    {
                    lv_optional_5_0=(Token)match(input,22,FOLLOW_71); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_optional_5_0, grammarAccess.getYAnnotAttrAccess().getOptionalQuestionMarkKeyword_5_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYAnnotAttrRule());
                      					}
                      					setWithLastConsumed(current, "optional", lv_optional_5_0, "?");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:4817:3: ( (lv_annots_6_0= ruleYAnnot ) )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( ((LA70_0>=74 && LA70_0<=80)) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // InternalLang.g:4818:4: (lv_annots_6_0= ruleYAnnot )
            	    {
            	    // InternalLang.g:4818:4: (lv_annots_6_0= ruleYAnnot )
            	    // InternalLang.g:4819:5: lv_annots_6_0= ruleYAnnot
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotAttrAccess().getAnnotsYAnnotParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_71);
            	    lv_annots_6_0=ruleYAnnot();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYAnnotAttrRule());
            	      					}
            	      					add(
            	      						current,
            	      						"annots",
            	      						lv_annots_6_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnot");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);

            // InternalLang.g:4836:3: (otherlv_7= ';' )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==12) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // InternalLang.g:4837:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getYAnnotAttrAccess().getSemicolonKeyword_7());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotAttr"


    // $ANTLR start "entryRuleYAnnotRel"
    // InternalLang.g:4846:1: entryRuleYAnnotRel returns [EObject current=null] : iv_ruleYAnnotRel= ruleYAnnotRel EOF ;
    public final EObject entryRuleYAnnotRel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotRel = null;


        try {
            // InternalLang.g:4846:50: (iv_ruleYAnnotRel= ruleYAnnotRel EOF )
            // InternalLang.g:4847:2: iv_ruleYAnnotRel= ruleYAnnotRel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotRelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotRel=ruleYAnnotRel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotRel; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotRel"


    // $ANTLR start "ruleYAnnotRel"
    // InternalLang.g:4853:1: ruleYAnnotRel returns [EObject current=null] : ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? ) ;
    public final EObject ruleYAnnotRel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_parent_2_0=null;
        Token lv_optional_4_0=null;
        Token otherlv_5=null;
        Token lv_many_7_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4859:2: ( ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? ) )
            // InternalLang.g:4860:2: ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? )
            {
            // InternalLang.g:4860:2: ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? )
            // InternalLang.g:4861:3: () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )?
            {
            // InternalLang.g:4861:3: ()
            // InternalLang.g:4862:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotRelAccess().getYAnnotRelAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,83,FOLLOW_72); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotRelAccess().getRelKeyword_1());
              		
            }
            // InternalLang.g:4872:3: ( (lv_parent_2_0= '@parent' ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==84) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalLang.g:4873:4: (lv_parent_2_0= '@parent' )
                    {
                    // InternalLang.g:4873:4: (lv_parent_2_0= '@parent' )
                    // InternalLang.g:4874:5: lv_parent_2_0= '@parent'
                    {
                    lv_parent_2_0=(Token)match(input,84,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_parent_2_0, grammarAccess.getYAnnotRelAccess().getParentParentKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYAnnotRelRule());
                      					}
                      					setWithLastConsumed(current, "parent", true, "@parent");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:4886:3: ( (lv_name_3_0= ruleValidID ) )
            // InternalLang.g:4887:4: (lv_name_3_0= ruleValidID )
            {
            // InternalLang.g:4887:4: (lv_name_3_0= ruleValidID )
            // InternalLang.g:4888:5: lv_name_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotRelAccess().getNameValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_73);
            lv_name_3_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotRelRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4905:3: ( (lv_optional_4_0= '?' ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==22) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalLang.g:4906:4: (lv_optional_4_0= '?' )
                    {
                    // InternalLang.g:4906:4: (lv_optional_4_0= '?' )
                    // InternalLang.g:4907:5: lv_optional_4_0= '?'
                    {
                    lv_optional_4_0=(Token)match(input,22,FOLLOW_60); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_optional_4_0, grammarAccess.getYAnnotRelAccess().getOptionalQuestionMarkKeyword_4_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYAnnotRelRule());
                      					}
                      					setWithLastConsumed(current, "optional", true, "?");
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYAnnotRelAccess().getHyphenMinusGreaterThanSignKeyword_5());
              		
            }
            // InternalLang.g:4923:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:4924:4: ( ruleQualifiedName )
            {
            // InternalLang.g:4924:4: ( ruleQualifiedName )
            // InternalLang.g:4925:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotRelRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotRelAccess().getTargetYAnnotEntityCrossReference_6_0());
              				
            }
            pushFollow(FOLLOW_74);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4939:3: ( (lv_many_7_0= '*' ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==44) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalLang.g:4940:4: (lv_many_7_0= '*' )
                    {
                    // InternalLang.g:4940:4: (lv_many_7_0= '*' )
                    // InternalLang.g:4941:5: lv_many_7_0= '*'
                    {
                    lv_many_7_0=(Token)match(input,44,FOLLOW_75); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_many_7_0, grammarAccess.getYAnnotRelAccess().getManyAsteriskKeyword_7_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYAnnotRelRule());
                      					}
                      					setWithLastConsumed(current, "many", true, "*");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:4953:3: (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==85) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalLang.g:4954:4: otherlv_8= '<-' ( ( ruleQualifiedName ) )
                    {
                    otherlv_8=(Token)match(input,85,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getYAnnotRelAccess().getLessThanSignHyphenMinusKeyword_8_0());
                      			
                    }
                    // InternalLang.g:4958:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:4959:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:4959:5: ( ruleQualifiedName )
                    // InternalLang.g:4960:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotRelRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotRelAccess().getInverseYAnnotRelCrossReference_8_1_0());
                      					
                    }
                    pushFollow(FOLLOW_30);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:4975:3: (otherlv_10= ';' )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==12) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalLang.g:4976:4: otherlv_10= ';'
                    {
                    otherlv_10=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_10, grammarAccess.getYAnnotRelAccess().getSemicolonKeyword_9());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotRel"


    // $ANTLR start "entryRuleYAnnotId"
    // InternalLang.g:4985:1: entryRuleYAnnotId returns [EObject current=null] : iv_ruleYAnnotId= ruleYAnnotId EOF ;
    public final EObject entryRuleYAnnotId() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotId = null;


        try {
            // InternalLang.g:4985:49: (iv_ruleYAnnotId= ruleYAnnotId EOF )
            // InternalLang.g:4986:2: iv_ruleYAnnotId= ruleYAnnotId EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotIdRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotId=ruleYAnnotId();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotId; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotId"


    // $ANTLR start "ruleYAnnotId"
    // InternalLang.g:4992:1: ruleYAnnotId returns [EObject current=null] : ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? ) ;
    public final EObject ruleYAnnotId() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4998:2: ( ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? ) )
            // InternalLang.g:4999:2: ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? )
            {
            // InternalLang.g:4999:2: ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? )
            // InternalLang.g:5000:3: () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )?
            {
            // InternalLang.g:5000:3: ()
            // InternalLang.g:5001:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotIdAccess().getYAnnotIdAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,86,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotIdAccess().getIdKeyword_1());
              		
            }
            // InternalLang.g:5011:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5012:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5012:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5013:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotIdAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_25);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotIdRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotIdAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalLang.g:5034:3: ( (otherlv_4= RULE_ID ) )
            // InternalLang.g:5035:4: (otherlv_4= RULE_ID )
            {
            // InternalLang.g:5035:4: (otherlv_4= RULE_ID )
            // InternalLang.g:5036:5: otherlv_4= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotIdRule());
              					}
              				
            }
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_27); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_4, grammarAccess.getYAnnotIdAccess().getAnnotsYAnnotEntityInnerCrossReference_4_0());
              				
            }

            }


            }

            // InternalLang.g:5047:3: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==24) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // InternalLang.g:5048:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
            	    {
            	    otherlv_5=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getYAnnotIdAccess().getCommaKeyword_5_0());
            	      			
            	    }
            	    // InternalLang.g:5052:4: ( (otherlv_6= RULE_ID ) )
            	    // InternalLang.g:5053:5: (otherlv_6= RULE_ID )
            	    {
            	    // InternalLang.g:5053:5: (otherlv_6= RULE_ID )
            	    // InternalLang.g:5054:6: otherlv_6= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getYAnnotIdRule());
            	      						}
            	      					
            	    }
            	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_27); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(otherlv_6, grammarAccess.getYAnnotIdAccess().getAnnotsYAnnotEntityInnerCrossReference_5_1_0());
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);

            otherlv_7=(Token)match(input,28,FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYAnnotIdAccess().getRightParenthesisKeyword_6());
              		
            }
            // InternalLang.g:5070:3: (otherlv_8= ';' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==12) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalLang.g:5071:4: otherlv_8= ';'
                    {
                    otherlv_8=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getYAnnotIdAccess().getSemicolonKeyword_7());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotId"


    // $ANTLR start "entryRuleYAnnotTechnicalDesign"
    // InternalLang.g:5080:1: entryRuleYAnnotTechnicalDesign returns [EObject current=null] : iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF ;
    public final EObject entryRuleYAnnotTechnicalDesign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTechnicalDesign = null;


        try {
            // InternalLang.g:5080:62: (iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF )
            // InternalLang.g:5081:2: iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotTechnicalDesignRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotTechnicalDesign=ruleYAnnotTechnicalDesign();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotTechnicalDesign; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotTechnicalDesign"


    // $ANTLR start "ruleYAnnotTechnicalDesign"
    // InternalLang.g:5087:1: ruleYAnnotTechnicalDesign returns [EObject current=null] : ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' ) ;
    public final EObject ruleYAnnotTechnicalDesign() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_features_5_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5093:2: ( ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' ) )
            // InternalLang.g:5094:2: ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' )
            {
            // InternalLang.g:5094:2: ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' )
            // InternalLang.g:5095:3: () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}'
            {
            // InternalLang.g:5095:3: ()
            // InternalLang.g:5096:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotTechnicalDesignAccess().getYAnnotTechnicalDesignAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,87,FOLLOW_76); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotTechnicalDesignAccess().getTdKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,88,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotTechnicalDesignAccess().getDatabaseKeyword_2());
              		
            }
            // InternalLang.g:5110:3: ( (otherlv_3= RULE_ID ) )
            // InternalLang.g:5111:4: (otherlv_3= RULE_ID )
            {
            // InternalLang.g:5111:4: (otherlv_3= RULE_ID )
            // InternalLang.g:5112:5: otherlv_3= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotTechnicalDesignRule());
              					}
              				
            }
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_3, grammarAccess.getYAnnotTechnicalDesignAccess().getDatabaseYAnnotDatabaseCrossReference_3_0());
              				
            }

            }


            }

            otherlv_4=(Token)match(input,19,FOLLOW_77); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotTechnicalDesignAccess().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalLang.g:5127:3: ( (lv_features_5_0= ruleYAnnotTable ) )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==89) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // InternalLang.g:5128:4: (lv_features_5_0= ruleYAnnotTable )
            	    {
            	    // InternalLang.g:5128:4: (lv_features_5_0= ruleYAnnotTable )
            	    // InternalLang.g:5129:5: lv_features_5_0= ruleYAnnotTable
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTechnicalDesignAccess().getFeaturesYAnnotTableParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_77);
            	    lv_features_5_0=ruleYAnnotTable();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYAnnotTechnicalDesignRule());
            	      					}
            	      					add(
            	      						current,
            	      						"features",
            	      						lv_features_5_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnotTable");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);

            otherlv_6=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYAnnotTechnicalDesignAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotTechnicalDesign"


    // $ANTLR start "entryRuleYAnnotTable"
    // InternalLang.g:5154:1: entryRuleYAnnotTable returns [EObject current=null] : iv_ruleYAnnotTable= ruleYAnnotTable EOF ;
    public final EObject entryRuleYAnnotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTable = null;


        try {
            // InternalLang.g:5154:52: (iv_ruleYAnnotTable= ruleYAnnotTable EOF )
            // InternalLang.g:5155:2: iv_ruleYAnnotTable= ruleYAnnotTable EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotTableRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotTable=ruleYAnnotTable();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotTable; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotTable"


    // $ANTLR start "ruleYAnnotTable"
    // InternalLang.g:5161:1: ruleYAnnotTable returns [EObject current=null] : ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' ) ;
    public final EObject ruleYAnnotTable() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_columns_6_0 = null;

        EObject lv_primarykey_7_0 = null;

        EObject lv_foreignkeys_8_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5167:2: ( ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' ) )
            // InternalLang.g:5168:2: ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' )
            {
            // InternalLang.g:5168:2: ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' )
            // InternalLang.g:5169:3: () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}'
            {
            // InternalLang.g:5169:3: ()
            // InternalLang.g:5170:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotTableAccess().getYAnnotTableAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,89,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotTableAccess().getTableKeyword_1());
              		
            }
            // InternalLang.g:5180:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5181:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5181:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5182:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotTableAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_60);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotTableRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotTableAccess().getHyphenMinusGreaterThanSignKeyword_3());
              		
            }
            // InternalLang.g:5203:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5204:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5204:4: ( ruleQualifiedName )
            // InternalLang.g:5205:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotTableRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotTableAccess().getEntityrefYAnnotEntityCrossReference_4_0());
              				
            }
            pushFollow(FOLLOW_13);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_5=(Token)match(input,19,FOLLOW_78); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYAnnotTableAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalLang.g:5223:3: ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==101) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalLang.g:5224:4: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    {
            	    // InternalLang.g:5224:4: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    // InternalLang.g:5225:5: lv_columns_6_0= ruleYAnnotAbstractColumn
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getColumnsYAnnotAbstractColumnParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_78);
            	    lv_columns_6_0=ruleYAnnotAbstractColumn();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYAnnotTableRule());
            	      					}
            	      					add(
            	      						current,
            	      						"columns",
            	      						lv_columns_6_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnotAbstractColumn");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);

            // InternalLang.g:5242:3: ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==102) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalLang.g:5243:4: (lv_primarykey_7_0= ruleYAnnotPrimaryKey )
                    {
                    // InternalLang.g:5243:4: (lv_primarykey_7_0= ruleYAnnotPrimaryKey )
                    // InternalLang.g:5244:5: lv_primarykey_7_0= ruleYAnnotPrimaryKey
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getPrimarykeyYAnnotPrimaryKeyParserRuleCall_7_0());
                      				
                    }
                    pushFollow(FOLLOW_79);
                    lv_primarykey_7_0=ruleYAnnotPrimaryKey();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYAnnotTableRule());
                      					}
                      					set(
                      						current,
                      						"primarykey",
                      						lv_primarykey_7_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YAnnotPrimaryKey");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:5261:3: ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==103) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // InternalLang.g:5262:4: (lv_foreignkeys_8_0= ruleYAnnotForeignKey )
            	    {
            	    // InternalLang.g:5262:4: (lv_foreignkeys_8_0= ruleYAnnotForeignKey )
            	    // InternalLang.g:5263:5: lv_foreignkeys_8_0= ruleYAnnotForeignKey
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getForeignkeysYAnnotForeignKeyParserRuleCall_8_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_79);
            	    lv_foreignkeys_8_0=ruleYAnnotForeignKey();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getYAnnotTableRule());
            	      					}
            	      					add(
            	      						current,
            	      						"foreignkeys",
            	      						lv_foreignkeys_8_0,
            	      						"eu.jgen.notes.dmw.lite.Lang.YAnnotForeignKey");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);

            otherlv_9=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getYAnnotTableAccess().getRightCurlyBracketKeyword_9());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotTable"


    // $ANTLR start "entryRuleYAnnotColumn"
    // InternalLang.g:5288:1: entryRuleYAnnotColumn returns [EObject current=null] : iv_ruleYAnnotColumn= ruleYAnnotColumn EOF ;
    public final EObject entryRuleYAnnotColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotColumn = null;


        try {
            // InternalLang.g:5288:53: (iv_ruleYAnnotColumn= ruleYAnnotColumn EOF )
            // InternalLang.g:5289:2: iv_ruleYAnnotColumn= ruleYAnnotColumn EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotColumnRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotColumn=ruleYAnnotColumn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotColumn; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotColumn"


    // $ANTLR start "ruleYAnnotColumn"
    // InternalLang.g:5295:1: ruleYAnnotColumn returns [EObject current=null] : ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? ) ;
    public final EObject ruleYAnnotColumn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_type_4_1=null;
        Token lv_type_4_2=null;
        Token lv_type_4_3=null;
        Token lv_type_4_4=null;
        Token lv_type_4_5=null;
        Token lv_type_4_6=null;
        Token lv_type_4_7=null;
        Token lv_type_4_8=null;
        Token lv_type_4_9=null;
        Token lv_type_4_10=null;
        Token lv_optional_5_0=null;
        Token otherlv_7=null;
        EObject lv_annots_6_1 = null;

        EObject lv_annots_6_2 = null;



        	enterRule();

        try {
            // InternalLang.g:5301:2: ( ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? ) )
            // InternalLang.g:5302:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? )
            {
            // InternalLang.g:5302:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? )
            // InternalLang.g:5303:3: () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )?
            {
            // InternalLang.g:5303:3: ()
            // InternalLang.g:5304:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotColumnAccess().getYAnnotColumnAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotColumnAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:5314:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5315:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5315:4: ( ruleQualifiedName )
            // InternalLang.g:5316:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotColumnRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAttrrefYAnnotAttrCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_80);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,90,FOLLOW_81); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotColumnAccess().getAsKeyword_3());
              		
            }
            // InternalLang.g:5334:3: ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) )
            // InternalLang.g:5335:4: ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) )
            {
            // InternalLang.g:5335:4: ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) )
            // InternalLang.g:5336:5: (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' )
            {
            // InternalLang.g:5336:5: (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' )
            int alt83=10;
            switch ( input.LA(1) ) {
            case 91:
                {
                alt83=1;
                }
                break;
            case 92:
                {
                alt83=2;
                }
                break;
            case 93:
                {
                alt83=3;
                }
                break;
            case 94:
                {
                alt83=4;
                }
                break;
            case 95:
                {
                alt83=5;
                }
                break;
            case 96:
                {
                alt83=6;
                }
                break;
            case 97:
                {
                alt83=7;
                }
                break;
            case 98:
                {
                alt83=8;
                }
                break;
            case 99:
                {
                alt83=9;
                }
                break;
            case 100:
                {
                alt83=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // InternalLang.g:5337:6: lv_type_4_1= 'VARCHAR'
                    {
                    lv_type_4_1=(Token)match(input,91,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_1, grammarAccess.getYAnnotColumnAccess().getTypeVARCHARKeyword_4_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_1, null);
                      					
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:5348:6: lv_type_4_2= 'CHAR'
                    {
                    lv_type_4_2=(Token)match(input,92,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_2, grammarAccess.getYAnnotColumnAccess().getTypeCHARKeyword_4_0_1());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_2, null);
                      					
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:5359:6: lv_type_4_3= 'INTEGER'
                    {
                    lv_type_4_3=(Token)match(input,93,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_3, grammarAccess.getYAnnotColumnAccess().getTypeINTEGERKeyword_4_0_2());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_3, null);
                      					
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:5370:6: lv_type_4_4= 'BIGINT'
                    {
                    lv_type_4_4=(Token)match(input,94,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_4, grammarAccess.getYAnnotColumnAccess().getTypeBIGINTKeyword_4_0_3());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_4, null);
                      					
                    }

                    }
                    break;
                case 5 :
                    // InternalLang.g:5381:6: lv_type_4_5= 'SMALLINT'
                    {
                    lv_type_4_5=(Token)match(input,95,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_5, grammarAccess.getYAnnotColumnAccess().getTypeSMALLINTKeyword_4_0_4());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_5, null);
                      					
                    }

                    }
                    break;
                case 6 :
                    // InternalLang.g:5392:6: lv_type_4_6= 'DECIMAL'
                    {
                    lv_type_4_6=(Token)match(input,96,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_6, grammarAccess.getYAnnotColumnAccess().getTypeDECIMALKeyword_4_0_5());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_6, null);
                      					
                    }

                    }
                    break;
                case 7 :
                    // InternalLang.g:5403:6: lv_type_4_7= 'TIME'
                    {
                    lv_type_4_7=(Token)match(input,97,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_7, grammarAccess.getYAnnotColumnAccess().getTypeTIMEKeyword_4_0_6());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_7, null);
                      					
                    }

                    }
                    break;
                case 8 :
                    // InternalLang.g:5414:6: lv_type_4_8= 'DATE'
                    {
                    lv_type_4_8=(Token)match(input,98,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_8, grammarAccess.getYAnnotColumnAccess().getTypeDATEKeyword_4_0_7());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_8, null);
                      					
                    }

                    }
                    break;
                case 9 :
                    // InternalLang.g:5425:6: lv_type_4_9= 'TIMESTAMP'
                    {
                    lv_type_4_9=(Token)match(input,99,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_9, grammarAccess.getYAnnotColumnAccess().getTypeTIMESTAMPKeyword_4_0_8());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_9, null);
                      					
                    }

                    }
                    break;
                case 10 :
                    // InternalLang.g:5436:6: lv_type_4_10= 'BOOLEAN'
                    {
                    lv_type_4_10=(Token)match(input,100,FOLLOW_82); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_type_4_10, grammarAccess.getYAnnotColumnAccess().getTypeBOOLEANKeyword_4_0_9());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      						}
                      						setWithLastConsumed(current, "type", lv_type_4_10, null);
                      					
                    }

                    }
                    break;

            }


            }


            }

            // InternalLang.g:5449:3: ( (lv_optional_5_0= '?' ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==22) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalLang.g:5450:4: (lv_optional_5_0= '?' )
                    {
                    // InternalLang.g:5450:4: (lv_optional_5_0= '?' )
                    // InternalLang.g:5451:5: lv_optional_5_0= '?'
                    {
                    lv_optional_5_0=(Token)match(input,22,FOLLOW_83); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_optional_5_0, grammarAccess.getYAnnotColumnAccess().getOptionalQuestionMarkKeyword_5_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getYAnnotColumnRule());
                      					}
                      					setWithLastConsumed(current, "optional", lv_optional_5_0, "?");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:5463:3: ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==74||LA86_0==76) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // InternalLang.g:5464:4: ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) )
            	    {
            	    // InternalLang.g:5464:4: ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) )
            	    // InternalLang.g:5465:5: (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal )
            	    {
            	    // InternalLang.g:5465:5: (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal )
            	    int alt85=2;
            	    int LA85_0 = input.LA(1);

            	    if ( (LA85_0==74) ) {
            	        alt85=1;
            	    }
            	    else if ( (LA85_0==76) ) {
            	        alt85=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 85, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt85) {
            	        case 1 :
            	            // InternalLang.g:5466:6: lv_annots_6_1= ruleYAnnotLength
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAnnotsYAnnotLengthParserRuleCall_6_0_0());
            	              					
            	            }
            	            pushFollow(FOLLOW_83);
            	            lv_annots_6_1=ruleYAnnotLength();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						if (current==null) {
            	              							current = createModelElementForParent(grammarAccess.getYAnnotColumnRule());
            	              						}
            	              						add(
            	              							current,
            	              							"annots",
            	              							lv_annots_6_1,
            	              							"eu.jgen.notes.dmw.lite.Lang.YAnnotLength");
            	              						afterParserOrEnumRuleCall();
            	              					
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalLang.g:5482:6: lv_annots_6_2= ruleYAnnotDecimal
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAnnotsYAnnotDecimalParserRuleCall_6_0_1());
            	              					
            	            }
            	            pushFollow(FOLLOW_83);
            	            lv_annots_6_2=ruleYAnnotDecimal();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						if (current==null) {
            	              							current = createModelElementForParent(grammarAccess.getYAnnotColumnRule());
            	              						}
            	              						add(
            	              							current,
            	              							"annots",
            	              							lv_annots_6_2,
            	              							"eu.jgen.notes.dmw.lite.Lang.YAnnotDecimal");
            	              						afterParserOrEnumRuleCall();
            	              					
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);

            // InternalLang.g:5500:3: (otherlv_7= ';' )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==12) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalLang.g:5501:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getYAnnotColumnAccess().getSemicolonKeyword_7());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotColumn"


    // $ANTLR start "entryRuleYAnnotColumnLike"
    // InternalLang.g:5510:1: entryRuleYAnnotColumnLike returns [EObject current=null] : iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF ;
    public final EObject entryRuleYAnnotColumnLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotColumnLike = null;


        try {
            // InternalLang.g:5510:57: (iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF )
            // InternalLang.g:5511:2: iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotColumnLikeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotColumnLike=ruleYAnnotColumnLike();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotColumnLike; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotColumnLike"


    // $ANTLR start "ruleYAnnotColumnLike"
    // InternalLang.g:5517:1: ruleYAnnotColumnLike returns [EObject current=null] : ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? ) ;
    public final EObject ruleYAnnotColumnLike() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalLang.g:5523:2: ( ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? ) )
            // InternalLang.g:5524:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? )
            {
            // InternalLang.g:5524:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? )
            // InternalLang.g:5525:3: () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )?
            {
            // InternalLang.g:5525:3: ()
            // InternalLang.g:5526:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotColumnLikeAccess().getYAnnotColumnLikeAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotColumnLikeAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:5536:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5537:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5537:4: ( ruleQualifiedName )
            // InternalLang.g:5538:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotColumnLikeRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotColumnLikeAccess().getColumnrefYAnnotAbstractColumnCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_30);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:5552:3: (otherlv_3= ';' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==12) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalLang.g:5553:4: otherlv_3= ';'
                    {
                    otherlv_3=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getYAnnotColumnLikeAccess().getSemicolonKeyword_3());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotColumnLike"


    // $ANTLR start "entryRuleYAnnotAbstractColumn"
    // InternalLang.g:5562:1: entryRuleYAnnotAbstractColumn returns [EObject current=null] : iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF ;
    public final EObject entryRuleYAnnotAbstractColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAbstractColumn = null;


        try {
            // InternalLang.g:5562:61: (iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF )
            // InternalLang.g:5563:2: iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotAbstractColumnRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotAbstractColumn=ruleYAnnotAbstractColumn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotAbstractColumn; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotAbstractColumn"


    // $ANTLR start "ruleYAnnotAbstractColumn"
    // InternalLang.g:5569:1: ruleYAnnotAbstractColumn returns [EObject current=null] : ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) ) ;
    public final EObject ruleYAnnotAbstractColumn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_3_1 = null;

        EObject lv_type_3_2 = null;



        	enterRule();

        try {
            // InternalLang.g:5575:2: ( ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) ) )
            // InternalLang.g:5576:2: ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) )
            {
            // InternalLang.g:5576:2: ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) )
            // InternalLang.g:5577:3: () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) )
            {
            // InternalLang.g:5577:3: ()
            // InternalLang.g:5578:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAbstractColumnAccess().getYAnnotAbstractColumnAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,101,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotAbstractColumnAccess().getColumnKeyword_1());
              		
            }
            // InternalLang.g:5588:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5589:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5589:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5590:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotAbstractColumnAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_60);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotAbstractColumnRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:5607:3: ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) )
            // InternalLang.g:5608:4: ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) )
            {
            // InternalLang.g:5608:4: ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) )
            // InternalLang.g:5609:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )
            {
            // InternalLang.g:5609:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )
            int alt89=2;
            alt89 = dfa89.predict(input);
            switch (alt89) {
                case 1 :
                    // InternalLang.g:5610:6: lv_type_3_1= ruleYAnnotColumn
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAbstractColumnAccess().getTypeYAnnotColumnParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_3_1=ruleYAnnotColumn();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotAbstractColumnRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_3_1,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotColumn");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:5626:6: lv_type_3_2= ruleYAnnotColumnLike
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotAbstractColumnAccess().getTypeYAnnotColumnLikeParserRuleCall_3_0_1());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_type_3_2=ruleYAnnotColumnLike();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getYAnnotAbstractColumnRule());
                      						}
                      						set(
                      							current,
                      							"type",
                      							lv_type_3_2,
                      							"eu.jgen.notes.dmw.lite.Lang.YAnnotColumnLike");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }
                    break;

            }


            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotAbstractColumn"


    // $ANTLR start "entryRuleYAnnotPrimaryKey"
    // InternalLang.g:5648:1: entryRuleYAnnotPrimaryKey returns [EObject current=null] : iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF ;
    public final EObject entryRuleYAnnotPrimaryKey() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotPrimaryKey = null;


        try {
            // InternalLang.g:5648:57: (iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF )
            // InternalLang.g:5649:2: iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotPrimaryKeyRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotPrimaryKey=ruleYAnnotPrimaryKey();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotPrimaryKey; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotPrimaryKey"


    // $ANTLR start "ruleYAnnotPrimaryKey"
    // InternalLang.g:5655:1: ruleYAnnotPrimaryKey returns [EObject current=null] : ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? ) ;
    public final EObject ruleYAnnotPrimaryKey() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;


        	enterRule();

        try {
            // InternalLang.g:5661:2: ( ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? ) )
            // InternalLang.g:5662:2: ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? )
            {
            // InternalLang.g:5662:2: ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? )
            // InternalLang.g:5663:3: () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )?
            {
            // InternalLang.g:5663:3: ()
            // InternalLang.g:5664:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotPrimaryKeyAccess().getYAnnotPrimaryKeyAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,102,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotPrimaryKeyAccess().getPrimaryKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_84); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotPrimaryKeyAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:5678:3: ( (otherlv_3= RULE_ID ) )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==RULE_ID) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // InternalLang.g:5679:4: (otherlv_3= RULE_ID )
            	    {
            	    // InternalLang.g:5679:4: (otherlv_3= RULE_ID )
            	    // InternalLang.g:5680:5: otherlv_3= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElement(grammarAccess.getYAnnotPrimaryKeyRule());
            	      					}
            	      				
            	    }
            	    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_84); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_3, grammarAccess.getYAnnotPrimaryKeyAccess().getColumnsYAnnotAbstractColumnCrossReference_3_0());
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            // InternalLang.g:5691:3: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==24) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // InternalLang.g:5692:4: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_4, grammarAccess.getYAnnotPrimaryKeyAccess().getCommaKeyword_4_0());
            	      			
            	    }
            	    // InternalLang.g:5696:4: ( (otherlv_5= RULE_ID ) )
            	    // InternalLang.g:5697:5: (otherlv_5= RULE_ID )
            	    {
            	    // InternalLang.g:5697:5: (otherlv_5= RULE_ID )
            	    // InternalLang.g:5698:6: otherlv_5= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getYAnnotPrimaryKeyRule());
            	      						}
            	      					
            	    }
            	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_27); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(otherlv_5, grammarAccess.getYAnnotPrimaryKeyAccess().getColumnsYAnnotAbstractColumnCrossReference_4_1_0());
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop91;
                }
            } while (true);

            otherlv_6=(Token)match(input,28,FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYAnnotPrimaryKeyAccess().getRightParenthesisKeyword_5());
              		
            }
            // InternalLang.g:5714:3: (otherlv_7= ';' )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==12) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalLang.g:5715:4: otherlv_7= ';'
                    {
                    otherlv_7=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getYAnnotPrimaryKeyAccess().getSemicolonKeyword_6());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotPrimaryKey"


    // $ANTLR start "entryRuleYAnnotForeignKey"
    // InternalLang.g:5724:1: entryRuleYAnnotForeignKey returns [EObject current=null] : iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF ;
    public final EObject entryRuleYAnnotForeignKey() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotForeignKey = null;


        try {
            // InternalLang.g:5724:57: (iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF )
            // InternalLang.g:5725:2: iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotForeignKeyRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotForeignKey=ruleYAnnotForeignKey();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotForeignKey; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotForeignKey"


    // $ANTLR start "ruleYAnnotForeignKey"
    // InternalLang.g:5731:1: ruleYAnnotForeignKey returns [EObject current=null] : ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? ) ;
    public final EObject ruleYAnnotForeignKey() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_columns_4_0 = null;

        EObject lv_columns_6_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5737:2: ( ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? ) )
            // InternalLang.g:5738:2: ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? )
            {
            // InternalLang.g:5738:2: ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? )
            // InternalLang.g:5739:3: () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )?
            {
            // InternalLang.g:5739:3: ()
            // InternalLang.g:5740:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotForeignKeyAccess().getYAnnotForeignKeyAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,103,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotForeignKeyAccess().getForeignKeyword_1());
              		
            }
            // InternalLang.g:5750:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5751:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5751:4: ( ruleQualifiedName )
            // InternalLang.g:5752:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotForeignKeyRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotForeignKeyAccess().getRelationshipYAnnotRelCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_13);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,19,FOLLOW_85); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotForeignKeyAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:5770:3: ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) )
            // InternalLang.g:5771:4: (lv_columns_4_0= ruleYAnnotAbstractColumn )
            {
            // InternalLang.g:5771:4: (lv_columns_4_0= ruleYAnnotAbstractColumn )
            // InternalLang.g:5772:5: lv_columns_4_0= ruleYAnnotAbstractColumn
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotForeignKeyAccess().getColumnsYAnnotAbstractColumnParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_86);
            lv_columns_4_0=ruleYAnnotAbstractColumn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotForeignKeyRule());
              					}
              					add(
              						current,
              						"columns",
              						lv_columns_4_0,
              						"eu.jgen.notes.dmw.lite.Lang.YAnnotAbstractColumn");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:5789:3: (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==24) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // InternalLang.g:5790:4: otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )
            	    {
            	    otherlv_5=(Token)match(input,24,FOLLOW_85); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getYAnnotForeignKeyAccess().getCommaKeyword_5_0());
            	      			
            	    }
            	    // InternalLang.g:5794:4: ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )
            	    // InternalLang.g:5795:5: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    {
            	    // InternalLang.g:5795:5: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    // InternalLang.g:5796:6: lv_columns_6_0= ruleYAnnotAbstractColumn
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAnnotForeignKeyAccess().getColumnsYAnnotAbstractColumnParserRuleCall_5_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_86);
            	    lv_columns_6_0=ruleYAnnotAbstractColumn();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYAnnotForeignKeyRule());
            	      						}
            	      						add(
            	      							current,
            	      							"columns",
            	      							lv_columns_6_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YAnnotAbstractColumn");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop93;
                }
            } while (true);

            otherlv_7=(Token)match(input,20,FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYAnnotForeignKeyAccess().getRightCurlyBracketKeyword_6());
              		
            }
            // InternalLang.g:5818:3: (otherlv_8= ';' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==12) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalLang.g:5819:4: otherlv_8= ';'
                    {
                    otherlv_8=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getYAnnotForeignKeyAccess().getSemicolonKeyword_7());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotForeignKey"


    // $ANTLR start "entryRuleYAnnotJava"
    // InternalLang.g:5828:1: entryRuleYAnnotJava returns [EObject current=null] : iv_ruleYAnnotJava= ruleYAnnotJava EOF ;
    public final EObject entryRuleYAnnotJava() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotJava = null;


        try {
            // InternalLang.g:5828:51: (iv_ruleYAnnotJava= ruleYAnnotJava EOF )
            // InternalLang.g:5829:2: iv_ruleYAnnotJava= ruleYAnnotJava EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotJavaRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotJava=ruleYAnnotJava();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotJava; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotJava"


    // $ANTLR start "ruleYAnnotJava"
    // InternalLang.g:5835:1: ruleYAnnotJava returns [EObject current=null] : ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleYAnnotJava() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:5841:2: ( ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalLang.g:5842:2: ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalLang.g:5842:2: ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalLang.g:5843:3: () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            // InternalLang.g:5843:3: ()
            // InternalLang.g:5844:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotJavaAccess().getYAnnotJavaAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,104,FOLLOW_87); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotJavaAccess().getJavaKeyword_1());
              		
            }
            // InternalLang.g:5854:3: (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==105) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalLang.g:5855:4: otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,105,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAnnotJavaAccess().getUsesKeyword_2_0());
                      			
                    }
                    // InternalLang.g:5859:4: ( (otherlv_3= RULE_ID ) )
                    // InternalLang.g:5860:5: (otherlv_3= RULE_ID )
                    {
                    // InternalLang.g:5860:5: (otherlv_3= RULE_ID )
                    // InternalLang.g:5861:6: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotJavaRule());
                      						}
                      					
                    }
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_3, grammarAccess.getYAnnotJavaAccess().getDatabaseYAnnotDatabaseCrossReference_2_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:5873:3: (otherlv_4= ';' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==12) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalLang.g:5874:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getYAnnotJavaAccess().getSemicolonKeyword_3());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotJava"


    // $ANTLR start "entryRuleYAnnotSwift"
    // InternalLang.g:5883:1: entryRuleYAnnotSwift returns [EObject current=null] : iv_ruleYAnnotSwift= ruleYAnnotSwift EOF ;
    public final EObject entryRuleYAnnotSwift() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotSwift = null;


        try {
            // InternalLang.g:5883:52: (iv_ruleYAnnotSwift= ruleYAnnotSwift EOF )
            // InternalLang.g:5884:2: iv_ruleYAnnotSwift= ruleYAnnotSwift EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotSwiftRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotSwift=ruleYAnnotSwift();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotSwift; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotSwift"


    // $ANTLR start "ruleYAnnotSwift"
    // InternalLang.g:5890:1: ruleYAnnotSwift returns [EObject current=null] : ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? ) ;
    public final EObject ruleYAnnotSwift() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5896:2: ( ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? ) )
            // InternalLang.g:5897:2: ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? )
            {
            // InternalLang.g:5897:2: ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? )
            // InternalLang.g:5898:3: () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )?
            {
            // InternalLang.g:5898:3: ()
            // InternalLang.g:5899:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotSwiftAccess().getYAnnotSwiftAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,106,FOLLOW_88); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotSwiftAccess().getSwiftKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,107,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotSwiftAccess().getModuleKeyword_2());
              		
            }
            // InternalLang.g:5913:3: ( (lv_name_3_0= ruleValidID ) )
            // InternalLang.g:5914:4: (lv_name_3_0= ruleValidID )
            {
            // InternalLang.g:5914:4: (lv_name_3_0= ruleValidID )
            // InternalLang.g:5915:5: lv_name_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotSwiftAccess().getNameValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_87);
            lv_name_3_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotSwiftRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_3_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:5932:3: (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==105) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalLang.g:5933:4: otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,105,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getYAnnotSwiftAccess().getUsesKeyword_4_0());
                      			
                    }
                    // InternalLang.g:5937:4: ( (otherlv_5= RULE_ID ) )
                    // InternalLang.g:5938:5: (otherlv_5= RULE_ID )
                    {
                    // InternalLang.g:5938:5: (otherlv_5= RULE_ID )
                    // InternalLang.g:5939:6: otherlv_5= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotSwiftRule());
                      						}
                      					
                    }
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_5, grammarAccess.getYAnnotSwiftAccess().getDatabaseYAnnotDatabaseCrossReference_4_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:5951:3: (otherlv_6= ';' )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==12) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // InternalLang.g:5952:4: otherlv_6= ';'
                    {
                    otherlv_6=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getYAnnotSwiftAccess().getSemicolonKeyword_5());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotSwift"


    // $ANTLR start "entryRuleYAnnotDatabase"
    // InternalLang.g:5961:1: entryRuleYAnnotDatabase returns [EObject current=null] : iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF ;
    public final EObject entryRuleYAnnotDatabase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDatabase = null;


        try {
            // InternalLang.g:5961:55: (iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF )
            // InternalLang.g:5962:2: iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getYAnnotDatabaseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleYAnnotDatabase=ruleYAnnotDatabase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleYAnnotDatabase; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleYAnnotDatabase"


    // $ANTLR start "ruleYAnnotDatabase"
    // InternalLang.g:5968:1: ruleYAnnotDatabase returns [EObject current=null] : ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? ) ;
    public final EObject ruleYAnnotDatabase() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5974:2: ( ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? ) )
            // InternalLang.g:5975:2: ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? )
            {
            // InternalLang.g:5975:2: ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? )
            // InternalLang.g:5976:3: () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )?
            {
            // InternalLang.g:5976:3: ()
            // InternalLang.g:5977:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDatabaseAccess().getYAnnotDatabaseAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,108,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDatabaseAccess().getDatabaseKeyword_1());
              		
            }
            // InternalLang.g:5987:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5988:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5988:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5989:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotDatabaseAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_30);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAnnotDatabaseRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:6006:3: (otherlv_3= ';' )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==12) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalLang.g:6007:4: otherlv_3= ';'
                    {
                    otherlv_3=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getYAnnotDatabaseAccess().getSemicolonKeyword_3());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAnnotDatabase"


    // $ANTLR start "ruleYAccessLevel"
    // InternalLang.g:6016:1: ruleYAccessLevel returns [Enumerator current=null] : ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) ) ;
    public final Enumerator ruleYAccessLevel() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalLang.g:6022:2: ( ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) ) )
            // InternalLang.g:6023:2: ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) )
            {
            // InternalLang.g:6023:2: ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) )
            int alt100=3;
            switch ( input.LA(1) ) {
            case 109:
                {
                alt100=1;
                }
                break;
            case 110:
                {
                alt100=2;
                }
                break;
            case 111:
                {
                alt100=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // InternalLang.g:6024:3: (enumLiteral_0= 'private' )
                    {
                    // InternalLang.g:6024:3: (enumLiteral_0= 'private' )
                    // InternalLang.g:6025:4: enumLiteral_0= 'private'
                    {
                    enumLiteral_0=(Token)match(input,109,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getYAccessLevelAccess().getPRIVATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getYAccessLevelAccess().getPRIVATEEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLang.g:6032:3: (enumLiteral_1= 'protected' )
                    {
                    // InternalLang.g:6032:3: (enumLiteral_1= 'protected' )
                    // InternalLang.g:6033:4: enumLiteral_1= 'protected'
                    {
                    enumLiteral_1=(Token)match(input,110,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getYAccessLevelAccess().getPROTECTEDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getYAccessLevelAccess().getPROTECTEDEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalLang.g:6040:3: (enumLiteral_2= 'public' )
                    {
                    // InternalLang.g:6040:3: (enumLiteral_2= 'public' )
                    // InternalLang.g:6041:4: enumLiteral_2= 'public'
                    {
                    enumLiteral_2=(Token)match(input,111,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getYAccessLevelAccess().getPUBLICEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_2, grammarAccess.getYAccessLevelAccess().getPUBLICEnumLiteralDeclaration_2());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleYAccessLevel"

    // $ANTLR start synpred1_InternalLang
    public final void synpred1_InternalLang_fragment() throws RecognitionException {   
        // InternalLang.g:245:5: ( '.' )
        // InternalLang.g:245:6: '.'
        {
        match(input,14,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalLang

    // $ANTLR start synpred2_InternalLang
    public final void synpred2_InternalLang_fragment() throws RecognitionException {   
        // InternalLang.g:1381:5: ( 'else' )
        // InternalLang.g:1381:6: 'else'
        {
        match(input,32,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalLang

    // Delegated rules

    public final boolean synpred1_InternalLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA29 dfa29 = new DFA29(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA89 dfa89 = new DFA89(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\uffff\1\15\17\uffff";
    static final String dfa_3s = "\2\4\17\uffff";
    static final String dfa_4s = "\2\110\17\uffff";
    static final String dfa_5s = "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\1";
    static final String dfa_6s = "\21\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\2\15\27\uffff\1\2\1\16\1\uffff\1\17\15\uffff\6\15\1\3\3\uffff\1\11\1\uffff\1\4\2\uffff\1\5\1\10\1\6\1\uffff\1\7\3\uffff\1\12\1\13\1\14",
            "\3\15\5\uffff\1\15\1\uffff\1\15\2\uffff\1\20\2\uffff\1\15\10\uffff\3\15\1\uffff\1\15\15\uffff\7\15\3\uffff\1\15\1\uffff\1\15\2\uffff\3\15\1\uffff\1\15\3\uffff\3\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "1057:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDisassociateStatement_6= ruleYDisassociateStatement | this_YDeleteStatement_7= ruleYDeleteStatement | this_YReadEachStatement_8= ruleYReadEachStatement | this_YWhileStatement_9= ruleYWhileStatement | this_YRepeatWhileStatement_10= ruleYRepeatWhileStatement | this_YForInStatement_11= ruleYForInStatement | (this_YExpression_12= ruleYExpression (otherlv_13= ';' )? ) | this_YIfStatement_14= ruleYIfStatement | this_YSwitchStatement_15= ruleYSwitchStatement )";
        }
    }
    static final String dfa_8s = "\10\uffff";
    static final String dfa_9s = "\4\uffff\1\3\2\uffff\1\3";
    static final String dfa_10s = "\1\4\1\21\1\4\1\uffff\1\16\1\4\1\uffff\1\16";
    static final String dfa_11s = "\1\4\1\22\1\4\1\uffff\1\35\1\4\1\uffff\1\35";
    static final String dfa_12s = "\3\uffff\1\2\2\uffff\1\1\1\uffff";
    static final String dfa_13s = "\10\uffff}>";
    static final String[] dfa_14s = {
            "\1\1",
            "\1\2\1\3",
            "\1\4",
            "",
            "\1\5\16\uffff\1\6",
            "\1\7",
            "",
            "\1\5\16\uffff\1\6"
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "1604:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )";
        }
    }
    static final String dfa_15s = "\7\uffff";
    static final String dfa_16s = "\2\uffff\1\5\3\uffff\1\5";
    static final String dfa_17s = "\1\22\1\4\1\14\1\4\2\uffff\1\14";
    static final String dfa_18s = "\1\22\1\4\1\147\1\4\2\uffff\1\147";
    static final String dfa_19s = "\4\uffff\1\1\1\2\1\uffff";
    static final String dfa_20s = "\7\uffff}>";
    static final String[] dfa_21s = {
            "\1\1",
            "\1\2",
            "\1\5\1\uffff\1\3\5\uffff\1\5\3\uffff\1\5\101\uffff\1\4\12\uffff\3\5",
            "\1\6",
            "",
            "",
            "\1\5\1\uffff\1\3\5\uffff\1\5\3\uffff\1\5\101\uffff\1\4\12\uffff\3\5"
    };

    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final char[] dfa_18 = DFA.unpackEncodedStringToUnsignedChars(dfa_18s);
    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final short[][] dfa_21 = unpackEncodedStringArray(dfa_21s);

    class DFA89 extends DFA {

        public DFA89(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 89;
            this.eot = dfa_15;
            this.eof = dfa_16;
            this.min = dfa_17;
            this.max = dfa_18;
            this.accept = dfa_19;
            this.special = dfa_20;
            this.transition = dfa_21;
        }
        public String getDescription() {
            return "5609:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000013002L,0x0000150000820000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000012002L,0x0000150000820000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010002L,0x0000150000820000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004310000L,0x0000E00000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004300000L,0x0000E00000000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000C41000L,0x000000000001FC00L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000441000L,0x000000000001FC00L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000041000L,0x000000000001FC00L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000001000L,0x000000000001FC00L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000002000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000010000010L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000011000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000000C0010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xCA3F8002C0100070L,0x00000000000001C5L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x001FC00008000070L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x001FC00008001070L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x001F800000000070L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000C00120000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000008004002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x001FC00018000070L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000030002800002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0040000001000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0400000001000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000000100000L,0x00000000004C0000L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000401002L,0x000000000001FC00L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000001002L,0x000000000001FC00L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000000010L,0x0000000000100000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000100000001002L,0x0000000000200000L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000001002L,0x0000000000200000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000100000L,0x0000000002000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000100000L,0x000000E000000000L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000100000L,0x0000008000000000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000000L,0x0000001FF8000000L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000401002L,0x0000000000001400L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000001002L,0x0000000000001400L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000011000010L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000001100000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000001002L,0x0000020000000000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});

}