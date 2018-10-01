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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'package'", "';'", "'import'", "'.'", "'.*'", "'class'", "':'", "'->'", "'{'", "'}'", "'var'", "'?'", "'<'", "','", "'>'", "'func'", "'('", "')'", "'='", "'return'", "'if'", "'else'", "'switch'", "'default'", "'case'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", "'+'", "'-'", "'*'", "'/'", "'!'", "'true'", "'false'", "'self'", "'super'", "'null'", "'new'", "'read'", "'success'", "'not'", "'found'", "'each'", "'target'", "'create'", "'already'", "'exist'", "'update'", "'delete'", "'associate'", "'join'", "'where'", "'while'", "'repeat'", "'for'", "'in'", "'@length'", "'@max'", "'@decimal'", "'@default'", "'@action'", "'@message'", "'@msgtype'", "'@entity'", "'@attr'", "'@rel'", "'@parent'", "'<-'", "'@id'", "'@td'", "'database'", "'@table'", "'as'", "'VARCHAR'", "'CHAR'", "'INTEGER'", "'BIGINT'", "'SMALLINT'", "'DECIMAL'", "'TIME'", "'DATE'", "'TIMESTAMP'", "'BOOLEAN'", "'@column'", "'@primary'", "'@foreign'", "'@java'", "'uses'", "'@swift'", "'module'", "'@database'", "'private'", "'protected'", "'public'"
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
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
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

                if ( (LA4_0==78||LA4_0==84||LA4_0==101||LA4_0==103||LA4_0==105) ) {
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
    // InternalLang.g:334:1: ruleYParameter returns [EObject current=null] : this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] ;
    public final EObject ruleYParameter() throws RecognitionException {
        EObject current = null;

        EObject this_YTypedDeclaration_0 = null;



        	enterRule();

        try {
            // InternalLang.g:340:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] )
            // InternalLang.g:341:2: this_YTypedDeclaration_0= ruleYTypedDeclaration[$current]
            {
            if ( state.backtracking==0 ) {

              		if (current==null) {
              			current = createModelElement(grammarAccess.getYParameterRule());
              		}
              		newCompositeNode(grammarAccess.getYParameterAccess().getYTypedDeclarationParserRuleCall());
              	
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
    // InternalLang.g:355:1: entryRuleYArgumentValue returns [String current=null] : iv_ruleYArgumentValue= ruleYArgumentValue EOF ;
    public final String entryRuleYArgumentValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleYArgumentValue = null;


        try {
            // InternalLang.g:355:54: (iv_ruleYArgumentValue= ruleYArgumentValue EOF )
            // InternalLang.g:356:2: iv_ruleYArgumentValue= ruleYArgumentValue EOF
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
    // InternalLang.g:362:1: ruleYArgumentValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleYArgumentValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalLang.g:368:2: ( (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING ) )
            // InternalLang.g:369:2: (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING )
            {
            // InternalLang.g:369:2: (this_INT_0= RULE_INT | this_STRING_1= RULE_STRING )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_INT) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_STRING) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalLang.g:370:3: this_INT_0= RULE_INT
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
                    // InternalLang.g:378:3: this_STRING_1= RULE_STRING
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
    // InternalLang.g:389:1: entryRuleYClass returns [EObject current=null] : iv_ruleYClass= ruleYClass EOF ;
    public final EObject entryRuleYClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYClass = null;


        try {
            // InternalLang.g:389:47: (iv_ruleYClass= ruleYClass EOF )
            // InternalLang.g:390:2: iv_ruleYClass= ruleYClass EOF
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
    // InternalLang.g:396:1: ruleYClass returns [EObject current=null] : ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' ) ;
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
            // InternalLang.g:402:2: ( ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' ) )
            // InternalLang.g:403:2: ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' )
            {
            // InternalLang.g:403:2: ( () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}' )
            // InternalLang.g:404:3: () otherlv_1= 'class' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ':' ( ( ruleQualifiedName ) ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? otherlv_7= '{' ( (lv_inners_8_0= ruleYClass ) )* ( (lv_members_9_0= ruleYMember ) )* otherlv_10= '}'
            {
            // InternalLang.g:404:3: ()
            // InternalLang.g:405:4: 
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
            // InternalLang.g:415:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:416:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:416:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:417:5: lv_name_2_0= ruleValidID
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

            // InternalLang.g:434:3: (otherlv_3= ':' ( ( ruleQualifiedName ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalLang.g:435:4: otherlv_3= ':' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getYClassAccess().getColonKeyword_3_0());
                      			
                    }
                    // InternalLang.g:439:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:440:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:440:5: ( ruleQualifiedName )
                    // InternalLang.g:441:6: ruleQualifiedName
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

            // InternalLang.g:456:3: (otherlv_5= '->' ( ( ruleQualifiedName ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalLang.g:457:4: otherlv_5= '->' ( ( ruleQualifiedName ) )
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYClassAccess().getHyphenMinusGreaterThanSignKeyword_4_0());
                      			
                    }
                    // InternalLang.g:461:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:462:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:462:5: ( ruleQualifiedName )
                    // InternalLang.g:463:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYClassRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYClassAccess().getEntityYAnnotEntityCrossReference_4_1_0());
                      					
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
            // InternalLang.g:482:3: ( (lv_inners_8_0= ruleYClass ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==16) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalLang.g:483:4: (lv_inners_8_0= ruleYClass )
            	    {
            	    // InternalLang.g:483:4: (lv_inners_8_0= ruleYClass )
            	    // InternalLang.g:484:5: lv_inners_8_0= ruleYClass
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
            	    break loop11;
                }
            } while (true);

            // InternalLang.g:501:3: ( (lv_members_9_0= ruleYMember ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==21||LA12_0==26||(LA12_0>=106 && LA12_0<=108)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalLang.g:502:4: (lv_members_9_0= ruleYMember )
            	    {
            	    // InternalLang.g:502:4: (lv_members_9_0= ruleYMember )
            	    // InternalLang.g:503:5: lv_members_9_0= ruleYMember
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
            	    break loop12;
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
    // InternalLang.g:528:1: entryRuleYMember returns [EObject current=null] : iv_ruleYMember= ruleYMember EOF ;
    public final EObject entryRuleYMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYMember = null;


        try {
            // InternalLang.g:528:48: (iv_ruleYMember= ruleYMember EOF )
            // InternalLang.g:529:2: iv_ruleYMember= ruleYMember EOF
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
    // InternalLang.g:535:1: ruleYMember returns [EObject current=null] : (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction ) ;
    public final EObject ruleYMember() throws RecognitionException {
        EObject current = null;

        EObject this_YProperty_0 = null;

        EObject this_YFunction_1 = null;



        	enterRule();

        try {
            // InternalLang.g:541:2: ( (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction ) )
            // InternalLang.g:542:2: (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction )
            {
            // InternalLang.g:542:2: (this_YProperty_0= ruleYProperty | this_YFunction_1= ruleYFunction )
            int alt13=2;
            switch ( input.LA(1) ) {
            case 106:
                {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==26) ) {
                    alt13=2;
                }
                else if ( (LA13_1==21) ) {
                    alt13=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
                }
                break;
            case 107:
                {
                int LA13_2 = input.LA(2);

                if ( (LA13_2==21) ) {
                    alt13=1;
                }
                else if ( (LA13_2==26) ) {
                    alt13=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 2, input);

                    throw nvae;
                }
                }
                break;
            case 108:
                {
                int LA13_3 = input.LA(2);

                if ( (LA13_3==26) ) {
                    alt13=2;
                }
                else if ( (LA13_3==21) ) {
                    alt13=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 3, input);

                    throw nvae;
                }
                }
                break;
            case 21:
                {
                alt13=1;
                }
                break;
            case 26:
                {
                alt13=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalLang.g:543:3: this_YProperty_0= ruleYProperty
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
                    // InternalLang.g:552:3: this_YFunction_1= ruleYFunction
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
    // InternalLang.g:565:1: ruleYTypedDeclaration[EObject in_current] returns [EObject current=in_current] : ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) ) ;
    public final EObject ruleYTypedDeclaration(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalLang.g:571:2: ( ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) ) )
            // InternalLang.g:572:2: ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) )
            {
            // InternalLang.g:572:2: ( ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) ) )
            // InternalLang.g:573:3: ( (lv_name_0_0= ruleValidID ) ) otherlv_1= ':' ( ( ruleQualifiedName ) )
            {
            // InternalLang.g:573:3: ( (lv_name_0_0= ruleValidID ) )
            // InternalLang.g:574:4: (lv_name_0_0= ruleValidID )
            {
            // InternalLang.g:574:4: (lv_name_0_0= ruleValidID )
            // InternalLang.g:575:5: lv_name_0_0= ruleValidID
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
            // InternalLang.g:596:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:597:4: ( ruleQualifiedName )
            {
            // InternalLang.g:597:4: ( ruleQualifiedName )
            // InternalLang.g:598:5: ruleQualifiedName
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
    // InternalLang.g:616:1: entryRuleYProperty returns [EObject current=null] : iv_ruleYProperty= ruleYProperty EOF ;
    public final EObject entryRuleYProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYProperty = null;


        try {
            // InternalLang.g:616:50: (iv_ruleYProperty= ruleYProperty EOF )
            // InternalLang.g:617:2: iv_ruleYProperty= ruleYProperty EOF
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
    // InternalLang.g:623:1: ruleYProperty returns [EObject current=null] : ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' ) ;
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
            // InternalLang.g:629:2: ( ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' ) )
            // InternalLang.g:630:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' )
            {
            // InternalLang.g:630:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';' )
            // InternalLang.g:631:3: ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'var' this_YTypedDeclaration_2= ruleYTypedDeclaration[$current] ( (lv_tuples_3_0= ruleYTuples ) )? ( (lv_optional_4_0= '?' ) )? (otherlv_5= '->' ( ( ruleQualifiedName ) ) )? ( (lv_annotations_7_0= ruleYAnnot ) )* otherlv_8= ';'
            {
            // InternalLang.g:631:3: ( (lv_access_0_0= ruleYAccessLevel ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=106 && LA14_0<=108)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalLang.g:632:4: (lv_access_0_0= ruleYAccessLevel )
                    {
                    // InternalLang.g:632:4: (lv_access_0_0= ruleYAccessLevel )
                    // InternalLang.g:633:5: lv_access_0_0= ruleYAccessLevel
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
            // InternalLang.g:665:3: ( (lv_tuples_3_0= ruleYTuples ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalLang.g:666:4: (lv_tuples_3_0= ruleYTuples )
                    {
                    // InternalLang.g:666:4: (lv_tuples_3_0= ruleYTuples )
                    // InternalLang.g:667:5: lv_tuples_3_0= ruleYTuples
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

            // InternalLang.g:684:3: ( (lv_optional_4_0= '?' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==22) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalLang.g:685:4: (lv_optional_4_0= '?' )
                    {
                    // InternalLang.g:685:4: (lv_optional_4_0= '?' )
                    // InternalLang.g:686:5: lv_optional_4_0= '?'
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

            // InternalLang.g:698:3: (otherlv_5= '->' ( ( ruleQualifiedName ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalLang.g:699:4: otherlv_5= '->' ( ( ruleQualifiedName ) )
                    {
                    otherlv_5=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYPropertyAccess().getHyphenMinusGreaterThanSignKeyword_5_0());
                      			
                    }
                    // InternalLang.g:703:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:704:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:704:5: ( ruleQualifiedName )
                    // InternalLang.g:705:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYPropertyRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYPropertyAccess().getAttrYAnnotAttrCrossReference_5_1_0());
                      					
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

            // InternalLang.g:720:3: ( (lv_annotations_7_0= ruleYAnnot ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=71 && LA18_0<=77)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalLang.g:721:4: (lv_annotations_7_0= ruleYAnnot )
            	    {
            	    // InternalLang.g:721:4: (lv_annotations_7_0= ruleYAnnot )
            	    // InternalLang.g:722:5: lv_annotations_7_0= ruleYAnnot
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
            	    break loop18;
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
    // InternalLang.g:747:1: entryRuleYTuples returns [EObject current=null] : iv_ruleYTuples= ruleYTuples EOF ;
    public final EObject entryRuleYTuples() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYTuples = null;


        try {
            // InternalLang.g:747:48: (iv_ruleYTuples= ruleYTuples EOF )
            // InternalLang.g:748:2: iv_ruleYTuples= ruleYTuples EOF
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
    // InternalLang.g:754:1: ruleYTuples returns [EObject current=null] : ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' ) ;
    public final EObject ruleYTuples() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalLang.g:760:2: ( ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' ) )
            // InternalLang.g:761:2: ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' )
            {
            // InternalLang.g:761:2: ( () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>' )
            // InternalLang.g:762:3: () otherlv_1= '<' ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )? otherlv_5= '>'
            {
            // InternalLang.g:762:3: ()
            // InternalLang.g:763:4: 
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
            // InternalLang.g:773:3: ( ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalLang.g:774:4: ( (otherlv_2= RULE_ID ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )*
                    {
                    // InternalLang.g:774:4: ( (otherlv_2= RULE_ID ) )
                    // InternalLang.g:775:5: (otherlv_2= RULE_ID )
                    {
                    // InternalLang.g:775:5: (otherlv_2= RULE_ID )
                    // InternalLang.g:776:6: otherlv_2= RULE_ID
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

                    // InternalLang.g:787:4: (otherlv_3= ',' ( (otherlv_4= RULE_ID ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==24) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalLang.g:788:5: otherlv_3= ',' ( (otherlv_4= RULE_ID ) )
                    	    {
                    	    otherlv_3=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getYTuplesAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalLang.g:792:5: ( (otherlv_4= RULE_ID ) )
                    	    // InternalLang.g:793:6: (otherlv_4= RULE_ID )
                    	    {
                    	    // InternalLang.g:793:6: (otherlv_4= RULE_ID )
                    	    // InternalLang.g:794:7: otherlv_4= RULE_ID
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
                    	    break loop19;
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
    // InternalLang.g:815:1: entryRuleYFunction returns [EObject current=null] : iv_ruleYFunction= ruleYFunction EOF ;
    public final EObject entryRuleYFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYFunction = null;


        try {
            // InternalLang.g:815:50: (iv_ruleYFunction= ruleYFunction EOF )
            // InternalLang.g:816:2: iv_ruleYFunction= ruleYFunction EOF
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
    // InternalLang.g:822:1: ruleYFunction returns [EObject current=null] : ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) ) ;
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
            // InternalLang.g:828:2: ( ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) ) )
            // InternalLang.g:829:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) )
            {
            // InternalLang.g:829:2: ( ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) ) )
            // InternalLang.g:830:3: ( (lv_access_0_0= ruleYAccessLevel ) )? otherlv_1= 'func' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )? otherlv_7= ')' ( (lv_returnvalue_8_0= '->' ) )? ( ( ruleQualifiedName ) )? ( (lv_body_10_0= ruleYBlock ) )
            {
            // InternalLang.g:830:3: ( (lv_access_0_0= ruleYAccessLevel ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=106 && LA21_0<=108)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalLang.g:831:4: (lv_access_0_0= ruleYAccessLevel )
                    {
                    // InternalLang.g:831:4: (lv_access_0_0= ruleYAccessLevel )
                    // InternalLang.g:832:5: lv_access_0_0= ruleYAccessLevel
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
            // InternalLang.g:853:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:854:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:854:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:855:5: lv_name_2_0= ruleValidID
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
            // InternalLang.g:876:3: ( ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )* )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ID) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalLang.g:877:4: ( (lv_params_4_0= ruleYParameter ) ) (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )*
                    {
                    // InternalLang.g:877:4: ( (lv_params_4_0= ruleYParameter ) )
                    // InternalLang.g:878:5: (lv_params_4_0= ruleYParameter )
                    {
                    // InternalLang.g:878:5: (lv_params_4_0= ruleYParameter )
                    // InternalLang.g:879:6: lv_params_4_0= ruleYParameter
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

                    // InternalLang.g:896:4: (otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==24) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // InternalLang.g:897:5: otherlv_5= ',' ( (lv_params_6_0= ruleYParameter ) )
                    	    {
                    	    otherlv_5=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getYFunctionAccess().getCommaKeyword_4_1_0());
                    	      				
                    	    }
                    	    // InternalLang.g:901:5: ( (lv_params_6_0= ruleYParameter ) )
                    	    // InternalLang.g:902:6: (lv_params_6_0= ruleYParameter )
                    	    {
                    	    // InternalLang.g:902:6: (lv_params_6_0= ruleYParameter )
                    	    // InternalLang.g:903:7: lv_params_6_0= ruleYParameter
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
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_7=(Token)match(input,28,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYFunctionAccess().getRightParenthesisKeyword_5());
              		
            }
            // InternalLang.g:926:3: ( (lv_returnvalue_8_0= '->' ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==18) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalLang.g:927:4: (lv_returnvalue_8_0= '->' )
                    {
                    // InternalLang.g:927:4: (lv_returnvalue_8_0= '->' )
                    // InternalLang.g:928:5: lv_returnvalue_8_0= '->'
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

            // InternalLang.g:940:3: ( ( ruleQualifiedName ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalLang.g:941:4: ( ruleQualifiedName )
                    {
                    // InternalLang.g:941:4: ( ruleQualifiedName )
                    // InternalLang.g:942:5: ruleQualifiedName
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

            // InternalLang.g:956:3: ( (lv_body_10_0= ruleYBlock ) )
            // InternalLang.g:957:4: (lv_body_10_0= ruleYBlock )
            {
            // InternalLang.g:957:4: (lv_body_10_0= ruleYBlock )
            // InternalLang.g:958:5: lv_body_10_0= ruleYBlock
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
    // InternalLang.g:979:1: entryRuleYBlock returns [EObject current=null] : iv_ruleYBlock= ruleYBlock EOF ;
    public final EObject entryRuleYBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYBlock = null;


        try {
            // InternalLang.g:979:47: (iv_ruleYBlock= ruleYBlock EOF )
            // InternalLang.g:980:2: iv_ruleYBlock= ruleYBlock EOF
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
    // InternalLang.g:986:1: ruleYBlock returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' ) ;
    public final EObject ruleYBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_statements_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:992:2: ( ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' ) )
            // InternalLang.g:993:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' )
            {
            // InternalLang.g:993:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}' )
            // InternalLang.g:994:3: () otherlv_1= '{' ( (lv_statements_2_0= ruleYStatement ) )* otherlv_3= '}'
            {
            // InternalLang.g:994:3: ()
            // InternalLang.g:995:4: 
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
            // InternalLang.g:1005:3: ( (lv_statements_2_0= ruleYStatement ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=RULE_ID && LA26_0<=RULE_STRING)||(LA26_0>=30 && LA26_0<=31)||LA26_0==33||(LA26_0>=47 && LA26_0<=53)||LA26_0==59||(LA26_0>=62 && LA26_0<=64)||(LA26_0>=67 && LA26_0<=69)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalLang.g:1006:4: (lv_statements_2_0= ruleYStatement )
            	    {
            	    // InternalLang.g:1006:4: (lv_statements_2_0= ruleYStatement )
            	    // InternalLang.g:1007:5: lv_statements_2_0= ruleYStatement
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
            	    break loop26;
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
    // InternalLang.g:1032:1: entryRuleYStatement returns [EObject current=null] : iv_ruleYStatement= ruleYStatement EOF ;
    public final EObject entryRuleYStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYStatement = null;


        try {
            // InternalLang.g:1032:51: (iv_ruleYStatement= ruleYStatement EOF )
            // InternalLang.g:1033:2: iv_ruleYStatement= ruleYStatement EOF
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
    // InternalLang.g:1039:1: ruleYStatement returns [EObject current=null] : (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDeleteStatement_6= ruleYDeleteStatement | this_YReadEachStatement_7= ruleYReadEachStatement | this_YWhileStatement_8= ruleYWhileStatement | this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement | this_YForInStatement_10= ruleYForInStatement | (this_YExpression_11= ruleYExpression otherlv_12= ';' ) | this_YIfStatement_13= ruleYIfStatement | this_YSwitchStatement_14= ruleYSwitchStatement ) ;
    public final EObject ruleYStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_12=null;
        EObject this_YVariableDeclaration_0 = null;

        EObject this_YReturn_1 = null;

        EObject this_YReadStatement_2 = null;

        EObject this_YCreateStatement_3 = null;

        EObject this_YUpdateStatement_4 = null;

        EObject this_YAssociateStatement_5 = null;

        EObject this_YDeleteStatement_6 = null;

        EObject this_YReadEachStatement_7 = null;

        EObject this_YWhileStatement_8 = null;

        EObject this_YRepeatWhileStatement_9 = null;

        EObject this_YForInStatement_10 = null;

        EObject this_YExpression_11 = null;

        EObject this_YIfStatement_13 = null;

        EObject this_YSwitchStatement_14 = null;



        	enterRule();

        try {
            // InternalLang.g:1045:2: ( (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDeleteStatement_6= ruleYDeleteStatement | this_YReadEachStatement_7= ruleYReadEachStatement | this_YWhileStatement_8= ruleYWhileStatement | this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement | this_YForInStatement_10= ruleYForInStatement | (this_YExpression_11= ruleYExpression otherlv_12= ';' ) | this_YIfStatement_13= ruleYIfStatement | this_YSwitchStatement_14= ruleYSwitchStatement ) )
            // InternalLang.g:1046:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDeleteStatement_6= ruleYDeleteStatement | this_YReadEachStatement_7= ruleYReadEachStatement | this_YWhileStatement_8= ruleYWhileStatement | this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement | this_YForInStatement_10= ruleYForInStatement | (this_YExpression_11= ruleYExpression otherlv_12= ';' ) | this_YIfStatement_13= ruleYIfStatement | this_YSwitchStatement_14= ruleYSwitchStatement )
            {
            // InternalLang.g:1046:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDeleteStatement_6= ruleYDeleteStatement | this_YReadEachStatement_7= ruleYReadEachStatement | this_YWhileStatement_8= ruleYWhileStatement | this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement | this_YForInStatement_10= ruleYForInStatement | (this_YExpression_11= ruleYExpression otherlv_12= ';' ) | this_YIfStatement_13= ruleYIfStatement | this_YSwitchStatement_14= ruleYSwitchStatement )
            int alt27=14;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // InternalLang.g:1047:3: this_YVariableDeclaration_0= ruleYVariableDeclaration
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
                    // InternalLang.g:1056:3: this_YReturn_1= ruleYReturn
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
                    // InternalLang.g:1065:3: this_YReadStatement_2= ruleYReadStatement
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
                    // InternalLang.g:1074:3: this_YCreateStatement_3= ruleYCreateStatement
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
                    // InternalLang.g:1083:3: this_YUpdateStatement_4= ruleYUpdateStatement
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
                    // InternalLang.g:1092:3: this_YAssociateStatement_5= ruleYAssociateStatement
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
                    // InternalLang.g:1101:3: this_YDeleteStatement_6= ruleYDeleteStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYDeleteStatementParserRuleCall_6());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YDeleteStatement_6=ruleYDeleteStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YDeleteStatement_6;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 8 :
                    // InternalLang.g:1110:3: this_YReadEachStatement_7= ruleYReadEachStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYReadEachStatementParserRuleCall_7());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YReadEachStatement_7=ruleYReadEachStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YReadEachStatement_7;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 9 :
                    // InternalLang.g:1119:3: this_YWhileStatement_8= ruleYWhileStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYWhileStatementParserRuleCall_8());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YWhileStatement_8=ruleYWhileStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YWhileStatement_8;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 10 :
                    // InternalLang.g:1128:3: this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYRepeatWhileStatementParserRuleCall_9());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YRepeatWhileStatement_9=ruleYRepeatWhileStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YRepeatWhileStatement_9;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 11 :
                    // InternalLang.g:1137:3: this_YForInStatement_10= ruleYForInStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYForInStatementParserRuleCall_10());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YForInStatement_10=ruleYForInStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YForInStatement_10;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 12 :
                    // InternalLang.g:1146:3: (this_YExpression_11= ruleYExpression otherlv_12= ';' )
                    {
                    // InternalLang.g:1146:3: (this_YExpression_11= ruleYExpression otherlv_12= ';' )
                    // InternalLang.g:1147:4: this_YExpression_11= ruleYExpression otherlv_12= ';'
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getYStatementAccess().getYExpressionParserRuleCall_11_0());
                      			
                    }
                    pushFollow(FOLLOW_8);
                    this_YExpression_11=ruleYExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_YExpression_11;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    otherlv_12=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_12, grammarAccess.getYStatementAccess().getSemicolonKeyword_11_1());
                      			
                    }

                    }


                    }
                    break;
                case 13 :
                    // InternalLang.g:1161:3: this_YIfStatement_13= ruleYIfStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYIfStatementParserRuleCall_12());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YIfStatement_13=ruleYIfStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YIfStatement_13;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 14 :
                    // InternalLang.g:1170:3: this_YSwitchStatement_14= ruleYSwitchStatement
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getYStatementAccess().getYSwitchStatementParserRuleCall_13());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_YSwitchStatement_14=ruleYSwitchStatement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_YSwitchStatement_14;
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
    // InternalLang.g:1182:1: entryRuleYVariableDeclaration returns [EObject current=null] : iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF ;
    public final EObject entryRuleYVariableDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYVariableDeclaration = null;


        try {
            // InternalLang.g:1182:61: (iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF )
            // InternalLang.g:1183:2: iv_ruleYVariableDeclaration= ruleYVariableDeclaration EOF
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
    // InternalLang.g:1189:1: ruleYVariableDeclaration returns [EObject current=null] : (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleYVariableDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject this_YTypedDeclaration_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1195:2: ( (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' ) )
            // InternalLang.g:1196:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' )
            {
            // InternalLang.g:1196:2: (this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';' )
            // InternalLang.g:1197:3: this_YTypedDeclaration_0= ruleYTypedDeclaration[$current] otherlv_1= '=' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ';'
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              				current = createModelElement(grammarAccess.getYVariableDeclarationRule());
              			}
              			newCompositeNode(grammarAccess.getYVariableDeclarationAccess().getYTypedDeclarationParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_30);
            this_YTypedDeclaration_0=ruleYTypedDeclaration(current);

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YTypedDeclaration_0;
              			afterParserOrEnumRuleCall();
              		
            }
            otherlv_1=(Token)match(input,29,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYVariableDeclarationAccess().getEqualsSignKeyword_1());
              		
            }
            // InternalLang.g:1212:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:1213:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:1213:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:1214:5: lv_expression_2_0= ruleYOrExpression
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
    // InternalLang.g:1239:1: entryRuleYReturn returns [EObject current=null] : iv_ruleYReturn= ruleYReturn EOF ;
    public final EObject entryRuleYReturn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReturn = null;


        try {
            // InternalLang.g:1239:48: (iv_ruleYReturn= ruleYReturn EOF )
            // InternalLang.g:1240:2: iv_ruleYReturn= ruleYReturn EOF
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
    // InternalLang.g:1246:1: ruleYReturn returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' ) ;
    public final EObject ruleYReturn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1252:2: ( ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' ) )
            // InternalLang.g:1253:2: ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' )
            {
            // InternalLang.g:1253:2: ( () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';' )
            // InternalLang.g:1254:3: () otherlv_1= 'return' ( (lv_expression_2_0= ruleYOrExpression ) )? otherlv_3= ';'
            {
            // InternalLang.g:1254:3: ()
            // InternalLang.g:1255:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYReturnAccess().getYReturnAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,30,FOLLOW_32); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYReturnAccess().getReturnKeyword_1());
              		
            }
            // InternalLang.g:1265:3: ( (lv_expression_2_0= ruleYOrExpression ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_ID && LA28_0<=RULE_STRING)||LA28_0==27||(LA28_0>=46 && LA28_0<=52)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalLang.g:1266:4: (lv_expression_2_0= ruleYOrExpression )
                    {
                    // InternalLang.g:1266:4: (lv_expression_2_0= ruleYOrExpression )
                    // InternalLang.g:1267:5: lv_expression_2_0= ruleYOrExpression
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
    // InternalLang.g:1292:1: entryRuleYIfStatement returns [EObject current=null] : iv_ruleYIfStatement= ruleYIfStatement EOF ;
    public final EObject entryRuleYIfStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYIfStatement = null;


        try {
            // InternalLang.g:1292:53: (iv_ruleYIfStatement= ruleYIfStatement EOF )
            // InternalLang.g:1293:2: iv_ruleYIfStatement= ruleYIfStatement EOF
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
    // InternalLang.g:1299:1: ruleYIfStatement returns [EObject current=null] : (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? ) ;
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
            // InternalLang.g:1305:2: ( (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? ) )
            // InternalLang.g:1306:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? )
            {
            // InternalLang.g:1306:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )? )
            // InternalLang.g:1307:3: otherlv_0= 'if' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_thenBlock_4_0= ruleYBlock ) ) ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )?
            {
            otherlv_0=(Token)match(input,31,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYIfStatementAccess().getIfKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYIfStatementAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:1315:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:1316:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:1316:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:1317:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYIfStatementAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_33);
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
            // InternalLang.g:1338:3: ( (lv_thenBlock_4_0= ruleYBlock ) )
            // InternalLang.g:1339:4: (lv_thenBlock_4_0= ruleYBlock )
            {
            // InternalLang.g:1339:4: (lv_thenBlock_4_0= ruleYBlock )
            // InternalLang.g:1340:5: lv_thenBlock_4_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYIfStatementAccess().getThenBlockYBlockParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_34);
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

            // InternalLang.g:1357:3: ( ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==32) && (synpred2_InternalLang())) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalLang.g:1358:4: ( ( 'else' )=>otherlv_5= 'else' ) ( (lv_elseBlock_6_0= ruleYBlock ) )
                    {
                    // InternalLang.g:1358:4: ( ( 'else' )=>otherlv_5= 'else' )
                    // InternalLang.g:1359:5: ( 'else' )=>otherlv_5= 'else'
                    {
                    otherlv_5=(Token)match(input,32,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_5, grammarAccess.getYIfStatementAccess().getElseKeyword_5_0());
                      				
                    }

                    }

                    // InternalLang.g:1365:4: ( (lv_elseBlock_6_0= ruleYBlock ) )
                    // InternalLang.g:1366:5: (lv_elseBlock_6_0= ruleYBlock )
                    {
                    // InternalLang.g:1366:5: (lv_elseBlock_6_0= ruleYBlock )
                    // InternalLang.g:1367:6: lv_elseBlock_6_0= ruleYBlock
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
    // InternalLang.g:1389:1: entryRuleYSwitchStatement returns [EObject current=null] : iv_ruleYSwitchStatement= ruleYSwitchStatement EOF ;
    public final EObject entryRuleYSwitchStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSwitchStatement = null;


        try {
            // InternalLang.g:1389:57: (iv_ruleYSwitchStatement= ruleYSwitchStatement EOF )
            // InternalLang.g:1390:2: iv_ruleYSwitchStatement= ruleYSwitchStatement EOF
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
    // InternalLang.g:1396:1: ruleYSwitchStatement returns [EObject current=null] : ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' ) ;
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
            // InternalLang.g:1402:2: ( ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' ) )
            // InternalLang.g:1403:2: ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' )
            {
            // InternalLang.g:1403:2: ( () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}' )
            // InternalLang.g:1404:3: () otherlv_1= 'switch' ( (lv_switchExpression_2_0= ruleYExpression ) ) otherlv_3= '{' ( (lv_cases_4_0= ruleYSwitchCase ) )* (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )? otherlv_8= '}'
            {
            // InternalLang.g:1404:3: ()
            // InternalLang.g:1405:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYSwitchStatementAccess().getYSwitchStatementAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,33,FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYSwitchStatementAccess().getSwitchKeyword_1());
              		
            }
            // InternalLang.g:1415:3: ( (lv_switchExpression_2_0= ruleYExpression ) )
            // InternalLang.g:1416:4: (lv_switchExpression_2_0= ruleYExpression )
            {
            // InternalLang.g:1416:4: (lv_switchExpression_2_0= ruleYExpression )
            // InternalLang.g:1417:5: lv_switchExpression_2_0= ruleYExpression
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

            otherlv_3=(Token)match(input,19,FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYSwitchStatementAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:1438:3: ( (lv_cases_4_0= ruleYSwitchCase ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==17||LA30_0==35) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalLang.g:1439:4: (lv_cases_4_0= ruleYSwitchCase )
            	    {
            	    // InternalLang.g:1439:4: (lv_cases_4_0= ruleYSwitchCase )
            	    // InternalLang.g:1440:5: lv_cases_4_0= ruleYSwitchCase
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYSwitchStatementAccess().getCasesYSwitchCaseParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_36);
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
            	    break loop30;
                }
            } while (true);

            // InternalLang.g:1457:3: (otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==34) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalLang.g:1458:4: otherlv_5= 'default' otherlv_6= ':' ( (lv_default_7_0= ruleYBlock ) )
                    {
                    otherlv_5=(Token)match(input,34,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYSwitchStatementAccess().getDefaultKeyword_5_0());
                      			
                    }
                    otherlv_6=(Token)match(input,17,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getYSwitchStatementAccess().getColonKeyword_5_1());
                      			
                    }
                    // InternalLang.g:1466:4: ( (lv_default_7_0= ruleYBlock ) )
                    // InternalLang.g:1467:5: (lv_default_7_0= ruleYBlock )
                    {
                    // InternalLang.g:1467:5: (lv_default_7_0= ruleYBlock )
                    // InternalLang.g:1468:6: lv_default_7_0= ruleYBlock
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYSwitchStatementAccess().getDefaultYBlockParserRuleCall_5_2_0());
                      					
                    }
                    pushFollow(FOLLOW_37);
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
    // InternalLang.g:1494:1: entryRuleYSwitchCase returns [EObject current=null] : iv_ruleYSwitchCase= ruleYSwitchCase EOF ;
    public final EObject entryRuleYSwitchCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSwitchCase = null;


        try {
            // InternalLang.g:1494:52: (iv_ruleYSwitchCase= ruleYSwitchCase EOF )
            // InternalLang.g:1495:2: iv_ruleYSwitchCase= ruleYSwitchCase EOF
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
    // InternalLang.g:1501:1: ruleYSwitchCase returns [EObject current=null] : ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYSwitchCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_caseExpression_2_0 = null;

        EObject lv_then_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1507:2: ( ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:1508:2: ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:1508:2: ( () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) ) )
            // InternalLang.g:1509:3: () (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )? otherlv_3= ':' ( (lv_then_4_0= ruleYBlock ) )
            {
            // InternalLang.g:1509:3: ()
            // InternalLang.g:1510:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYSwitchCaseAccess().getYSwitchCaseAction_0(),
              					current);
              			
            }

            }

            // InternalLang.g:1516:3: (otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==35) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalLang.g:1517:4: otherlv_1= 'case' ( (lv_caseExpression_2_0= ruleYExpression ) )
                    {
                    otherlv_1=(Token)match(input,35,FOLLOW_35); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getYSwitchCaseAccess().getCaseKeyword_1_0());
                      			
                    }
                    // InternalLang.g:1521:4: ( (lv_caseExpression_2_0= ruleYExpression ) )
                    // InternalLang.g:1522:5: (lv_caseExpression_2_0= ruleYExpression )
                    {
                    // InternalLang.g:1522:5: (lv_caseExpression_2_0= ruleYExpression )
                    // InternalLang.g:1523:6: lv_caseExpression_2_0= ruleYExpression
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
            // InternalLang.g:1545:3: ( (lv_then_4_0= ruleYBlock ) )
            // InternalLang.g:1546:4: (lv_then_4_0= ruleYBlock )
            {
            // InternalLang.g:1546:4: (lv_then_4_0= ruleYBlock )
            // InternalLang.g:1547:5: lv_then_4_0= ruleYBlock
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
    // InternalLang.g:1568:1: entryRuleYSymbol returns [EObject current=null] : iv_ruleYSymbol= ruleYSymbol EOF ;
    public final EObject entryRuleYSymbol() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSymbol = null;


        try {
            // InternalLang.g:1568:48: (iv_ruleYSymbol= ruleYSymbol EOF )
            // InternalLang.g:1569:2: iv_ruleYSymbol= ruleYSymbol EOF
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
    // InternalLang.g:1575:1: ruleYSymbol returns [EObject current=null] : (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter ) ;
    public final EObject ruleYSymbol() throws RecognitionException {
        EObject current = null;

        EObject this_YVariableDeclaration_0 = null;

        EObject this_YParameter_1 = null;



        	enterRule();

        try {
            // InternalLang.g:1581:2: ( (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter ) )
            // InternalLang.g:1582:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )
            {
            // InternalLang.g:1582:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )
            int alt33=2;
            alt33 = dfa33.predict(input);
            switch (alt33) {
                case 1 :
                    // InternalLang.g:1583:3: this_YVariableDeclaration_0= ruleYVariableDeclaration
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
                    // InternalLang.g:1592:3: this_YParameter_1= ruleYParameter
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
    // InternalLang.g:1604:1: entryRuleYExpression returns [EObject current=null] : iv_ruleYExpression= ruleYExpression EOF ;
    public final EObject entryRuleYExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYExpression = null;


        try {
            // InternalLang.g:1604:52: (iv_ruleYExpression= ruleYExpression EOF )
            // InternalLang.g:1605:2: iv_ruleYExpression= ruleYExpression EOF
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
    // InternalLang.g:1611:1: ruleYExpression returns [EObject current=null] : this_YAssignment_0= ruleYAssignment ;
    public final EObject ruleYExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAssignment_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1617:2: (this_YAssignment_0= ruleYAssignment )
            // InternalLang.g:1618:2: this_YAssignment_0= ruleYAssignment
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
    // InternalLang.g:1629:1: entryRuleYAssignment returns [EObject current=null] : iv_ruleYAssignment= ruleYAssignment EOF ;
    public final EObject entryRuleYAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAssignment = null;


        try {
            // InternalLang.g:1629:52: (iv_ruleYAssignment= ruleYAssignment EOF )
            // InternalLang.g:1630:2: iv_ruleYAssignment= ruleYAssignment EOF
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
    // InternalLang.g:1636:1: ruleYAssignment returns [EObject current=null] : (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? ) ;
    public final EObject ruleYAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_YSelectionExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1642:2: ( (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? ) )
            // InternalLang.g:1643:2: (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? )
            {
            // InternalLang.g:1643:2: (this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )? )
            // InternalLang.g:1644:3: this_YSelectionExpression_0= ruleYSelectionExpression ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAssignmentAccess().getYSelectionExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_38);
            this_YSelectionExpression_0=ruleYSelectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YSelectionExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1652:3: ( () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==29) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalLang.g:1653:4: () otherlv_2= '=' ( (lv_right_3_0= ruleYOrExpression ) )
                    {
                    // InternalLang.g:1653:4: ()
                    // InternalLang.g:1654:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getYAssignmentAccess().getYAssignmentLeftAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,29,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAssignmentAccess().getEqualsSignKeyword_1_1());
                      			
                    }
                    // InternalLang.g:1664:4: ( (lv_right_3_0= ruleYOrExpression ) )
                    // InternalLang.g:1665:5: (lv_right_3_0= ruleYOrExpression )
                    {
                    // InternalLang.g:1665:5: (lv_right_3_0= ruleYOrExpression )
                    // InternalLang.g:1666:6: lv_right_3_0= ruleYOrExpression
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
    // InternalLang.g:1688:1: entryRuleYSelectionExpression returns [EObject current=null] : iv_ruleYSelectionExpression= ruleYSelectionExpression EOF ;
    public final EObject entryRuleYSelectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYSelectionExpression = null;


        try {
            // InternalLang.g:1688:61: (iv_ruleYSelectionExpression= ruleYSelectionExpression EOF )
            // InternalLang.g:1689:2: iv_ruleYSelectionExpression= ruleYSelectionExpression EOF
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
    // InternalLang.g:1695:1: ruleYSelectionExpression returns [EObject current=null] : (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* ) ;
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
            // InternalLang.g:1701:2: ( (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* ) )
            // InternalLang.g:1702:2: (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* )
            {
            // InternalLang.g:1702:2: (this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )* )
            // InternalLang.g:1703:3: this_YTerminalExpression_0= ruleYTerminalExpression ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )*
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
            // InternalLang.g:1711:3: ( () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )? )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==14) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalLang.g:1712:4: () otherlv_2= '.' ( (otherlv_3= RULE_ID ) ) ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )?
            	    {
            	    // InternalLang.g:1712:4: ()
            	    // InternalLang.g:1713:5: 
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
            	    // InternalLang.g:1723:4: ( (otherlv_3= RULE_ID ) )
            	    // InternalLang.g:1724:5: (otherlv_3= RULE_ID )
            	    {
            	    // InternalLang.g:1724:5: (otherlv_3= RULE_ID )
            	    // InternalLang.g:1725:6: otherlv_3= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getYSelectionExpressionRule());
            	      						}
            	      					
            	    }
            	    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_39); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(otherlv_3, grammarAccess.getYSelectionExpressionAccess().getMemberYMemberCrossReference_1_2_0());
            	      					
            	    }

            	    }


            	    }

            	    // InternalLang.g:1736:4: ( ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')' )?
            	    int alt37=2;
            	    int LA37_0 = input.LA(1);

            	    if ( (LA37_0==27) ) {
            	        alt37=1;
            	    }
            	    switch (alt37) {
            	        case 1 :
            	            // InternalLang.g:1737:5: ( (lv_functioninvocation_4_0= '(' ) ) ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )? otherlv_8= ')'
            	            {
            	            // InternalLang.g:1737:5: ( (lv_functioninvocation_4_0= '(' ) )
            	            // InternalLang.g:1738:6: (lv_functioninvocation_4_0= '(' )
            	            {
            	            // InternalLang.g:1738:6: (lv_functioninvocation_4_0= '(' )
            	            // InternalLang.g:1739:7: lv_functioninvocation_4_0= '('
            	            {
            	            lv_functioninvocation_4_0=(Token)match(input,27,FOLLOW_40); if (state.failed) return current;
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

            	            // InternalLang.g:1751:5: ( ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )* )?
            	            int alt36=2;
            	            int LA36_0 = input.LA(1);

            	            if ( ((LA36_0>=RULE_ID && LA36_0<=RULE_STRING)||LA36_0==27||(LA36_0>=46 && LA36_0<=52)) ) {
            	                alt36=1;
            	            }
            	            switch (alt36) {
            	                case 1 :
            	                    // InternalLang.g:1752:6: ( (lv_args_5_0= ruleYOrExpression ) ) (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )*
            	                    {
            	                    // InternalLang.g:1752:6: ( (lv_args_5_0= ruleYOrExpression ) )
            	                    // InternalLang.g:1753:7: (lv_args_5_0= ruleYOrExpression )
            	                    {
            	                    // InternalLang.g:1753:7: (lv_args_5_0= ruleYOrExpression )
            	                    // InternalLang.g:1754:8: lv_args_5_0= ruleYOrExpression
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

            	                    // InternalLang.g:1771:6: (otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) ) )*
            	                    loop35:
            	                    do {
            	                        int alt35=2;
            	                        int LA35_0 = input.LA(1);

            	                        if ( (LA35_0==24) ) {
            	                            alt35=1;
            	                        }


            	                        switch (alt35) {
            	                    	case 1 :
            	                    	    // InternalLang.g:1772:7: otherlv_6= ',' ( (lv_args_7_0= ruleYOrExpression ) )
            	                    	    {
            	                    	    otherlv_6=(Token)match(input,24,FOLLOW_31); if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      							newLeafNode(otherlv_6, grammarAccess.getYSelectionExpressionAccess().getCommaKeyword_1_3_1_1_0());
            	                    	      						
            	                    	    }
            	                    	    // InternalLang.g:1776:7: ( (lv_args_7_0= ruleYOrExpression ) )
            	                    	    // InternalLang.g:1777:8: (lv_args_7_0= ruleYOrExpression )
            	                    	    {
            	                    	    // InternalLang.g:1777:8: (lv_args_7_0= ruleYOrExpression )
            	                    	    // InternalLang.g:1778:9: lv_args_7_0= ruleYOrExpression
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
            	                    	    break loop35;
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
            	    break loop38;
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
    // InternalLang.g:1807:1: entryRuleYOrExpression returns [EObject current=null] : iv_ruleYOrExpression= ruleYOrExpression EOF ;
    public final EObject entryRuleYOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYOrExpression = null;


        try {
            // InternalLang.g:1807:54: (iv_ruleYOrExpression= ruleYOrExpression EOF )
            // InternalLang.g:1808:2: iv_ruleYOrExpression= ruleYOrExpression EOF
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
    // InternalLang.g:1814:1: ruleYOrExpression returns [EObject current=null] : (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* ) ;
    public final EObject ruleYOrExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAndExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1820:2: ( (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* ) )
            // InternalLang.g:1821:2: (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* )
            {
            // InternalLang.g:1821:2: (this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )* )
            // InternalLang.g:1822:3: this_YAndExpression_0= ruleYAndExpression ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYOrExpressionAccess().getYAndExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_41);
            this_YAndExpression_0=ruleYAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YAndExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1830:3: ( () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==36) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalLang.g:1831:4: () ruleOpOr ( (lv_right_3_0= ruleYAndExpression ) )
            	    {
            	    // InternalLang.g:1831:4: ()
            	    // InternalLang.g:1832:5: 
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
            	    pushFollow(FOLLOW_31);
            	    ruleOpOr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }
            	    // InternalLang.g:1845:4: ( (lv_right_3_0= ruleYAndExpression ) )
            	    // InternalLang.g:1846:5: (lv_right_3_0= ruleYAndExpression )
            	    {
            	    // InternalLang.g:1846:5: (lv_right_3_0= ruleYAndExpression )
            	    // InternalLang.g:1847:6: lv_right_3_0= ruleYAndExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYOrExpressionAccess().getRightYAndExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_41);
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
            	    break loop39;
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
    // InternalLang.g:1869:1: entryRuleOpOr returns [String current=null] : iv_ruleOpOr= ruleOpOr EOF ;
    public final String entryRuleOpOr() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOr = null;


        try {
            // InternalLang.g:1869:44: (iv_ruleOpOr= ruleOpOr EOF )
            // InternalLang.g:1870:2: iv_ruleOpOr= ruleOpOr EOF
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
    // InternalLang.g:1876:1: ruleOpOr returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '||' ;
    public final AntlrDatatypeRuleToken ruleOpOr() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:1882:2: (kw= '||' )
            // InternalLang.g:1883:2: kw= '||'
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
    // InternalLang.g:1891:1: entryRuleYAndExpression returns [EObject current=null] : iv_ruleYAndExpression= ruleYAndExpression EOF ;
    public final EObject entryRuleYAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAndExpression = null;


        try {
            // InternalLang.g:1891:55: (iv_ruleYAndExpression= ruleYAndExpression EOF )
            // InternalLang.g:1892:2: iv_ruleYAndExpression= ruleYAndExpression EOF
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
    // InternalLang.g:1898:1: ruleYAndExpression returns [EObject current=null] : (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* ) ;
    public final EObject ruleYAndExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YEqualityExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1904:2: ( (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* ) )
            // InternalLang.g:1905:2: (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* )
            {
            // InternalLang.g:1905:2: (this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )* )
            // InternalLang.g:1906:3: this_YEqualityExpression_0= ruleYEqualityExpression ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAndExpressionAccess().getYEqualityExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_42);
            this_YEqualityExpression_0=ruleYEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YEqualityExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1914:3: ( () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==37) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalLang.g:1915:4: () ruleOpAnd ( (lv_right_3_0= ruleYEqualityExpression ) )
            	    {
            	    // InternalLang.g:1915:4: ()
            	    // InternalLang.g:1916:5: 
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
            	    pushFollow(FOLLOW_31);
            	    ruleOpAnd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }
            	    // InternalLang.g:1929:4: ( (lv_right_3_0= ruleYEqualityExpression ) )
            	    // InternalLang.g:1930:5: (lv_right_3_0= ruleYEqualityExpression )
            	    {
            	    // InternalLang.g:1930:5: (lv_right_3_0= ruleYEqualityExpression )
            	    // InternalLang.g:1931:6: lv_right_3_0= ruleYEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAndExpressionAccess().getRightYEqualityExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_42);
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
    // $ANTLR end "ruleYAndExpression"


    // $ANTLR start "entryRuleOpAnd"
    // InternalLang.g:1953:1: entryRuleOpAnd returns [String current=null] : iv_ruleOpAnd= ruleOpAnd EOF ;
    public final String entryRuleOpAnd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAnd = null;


        try {
            // InternalLang.g:1953:45: (iv_ruleOpAnd= ruleOpAnd EOF )
            // InternalLang.g:1954:2: iv_ruleOpAnd= ruleOpAnd EOF
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
    // InternalLang.g:1960:1: ruleOpAnd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '&&' ;
    public final AntlrDatatypeRuleToken ruleOpAnd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:1966:2: (kw= '&&' )
            // InternalLang.g:1967:2: kw= '&&'
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
    // InternalLang.g:1975:1: entryRuleYEqualityExpression returns [EObject current=null] : iv_ruleYEqualityExpression= ruleYEqualityExpression EOF ;
    public final EObject entryRuleYEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYEqualityExpression = null;


        try {
            // InternalLang.g:1975:60: (iv_ruleYEqualityExpression= ruleYEqualityExpression EOF )
            // InternalLang.g:1976:2: iv_ruleYEqualityExpression= ruleYEqualityExpression EOF
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
    // InternalLang.g:1982:1: ruleYEqualityExpression returns [EObject current=null] : (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* ) ;
    public final EObject ruleYEqualityExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_YComparisonExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:1988:2: ( (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* ) )
            // InternalLang.g:1989:2: (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* )
            {
            // InternalLang.g:1989:2: (this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )* )
            // InternalLang.g:1990:3: this_YComparisonExpression_0= ruleYComparisonExpression ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYEqualityExpressionAccess().getYComparisonExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_43);
            this_YComparisonExpression_0=ruleYComparisonExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YComparisonExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:1998:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=38 && LA42_0<=39)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalLang.g:1999:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleYComparisonExpression ) )
            	    {
            	    // InternalLang.g:1999:4: ()
            	    // InternalLang.g:2000:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYEqualityExpressionAccess().getYEqualityExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2006:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalLang.g:2007:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalLang.g:2007:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalLang.g:2008:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalLang.g:2008:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==38) ) {
            	        alt41=1;
            	    }
            	    else if ( (LA41_0==39) ) {
            	        alt41=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 41, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // InternalLang.g:2009:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,38,FOLLOW_31); if (state.failed) return current;
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
            	            // InternalLang.g:2020:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,39,FOLLOW_31); if (state.failed) return current;
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

            	    // InternalLang.g:2033:4: ( (lv_right_3_0= ruleYComparisonExpression ) )
            	    // InternalLang.g:2034:5: (lv_right_3_0= ruleYComparisonExpression )
            	    {
            	    // InternalLang.g:2034:5: (lv_right_3_0= ruleYComparisonExpression )
            	    // InternalLang.g:2035:6: lv_right_3_0= ruleYComparisonExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYEqualityExpressionAccess().getRightYComparisonExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_43);
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
    // $ANTLR end "ruleYEqualityExpression"


    // $ANTLR start "entryRuleYComparisonExpression"
    // InternalLang.g:2057:1: entryRuleYComparisonExpression returns [EObject current=null] : iv_ruleYComparisonExpression= ruleYComparisonExpression EOF ;
    public final EObject entryRuleYComparisonExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYComparisonExpression = null;


        try {
            // InternalLang.g:2057:62: (iv_ruleYComparisonExpression= ruleYComparisonExpression EOF )
            // InternalLang.g:2058:2: iv_ruleYComparisonExpression= ruleYComparisonExpression EOF
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
    // InternalLang.g:2064:1: ruleYComparisonExpression returns [EObject current=null] : (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* ) ;
    public final EObject ruleYComparisonExpression() throws RecognitionException {
        EObject current = null;

        EObject this_YAdditiveExpression_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2070:2: ( (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* ) )
            // InternalLang.g:2071:2: (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* )
            {
            // InternalLang.g:2071:2: (this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )* )
            // InternalLang.g:2072:3: this_YAdditiveExpression_0= ruleYAdditiveExpression ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getYAdditiveExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_44);
            this_YAdditiveExpression_0=ruleYAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YAdditiveExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2080:3: ( () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==23||LA43_0==25||(LA43_0>=40 && LA43_0<=41)) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalLang.g:2081:4: () ( (lv_op_2_0= ruleOpCompare ) ) ( (lv_right_3_0= ruleYAdditiveExpression ) )
            	    {
            	    // InternalLang.g:2081:4: ()
            	    // InternalLang.g:2082:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYComparisonExpressionAccess().getYComparisonExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2088:4: ( (lv_op_2_0= ruleOpCompare ) )
            	    // InternalLang.g:2089:5: (lv_op_2_0= ruleOpCompare )
            	    {
            	    // InternalLang.g:2089:5: (lv_op_2_0= ruleOpCompare )
            	    // InternalLang.g:2090:6: lv_op_2_0= ruleOpCompare
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getOpOpCompareParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_31);
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

            	    // InternalLang.g:2107:4: ( (lv_right_3_0= ruleYAdditiveExpression ) )
            	    // InternalLang.g:2108:5: (lv_right_3_0= ruleYAdditiveExpression )
            	    {
            	    // InternalLang.g:2108:5: (lv_right_3_0= ruleYAdditiveExpression )
            	    // InternalLang.g:2109:6: lv_right_3_0= ruleYAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYComparisonExpressionAccess().getRightYAdditiveExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_44);
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
            	    break loop43;
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
    // InternalLang.g:2131:1: entryRuleOpCompare returns [String current=null] : iv_ruleOpCompare= ruleOpCompare EOF ;
    public final String entryRuleOpCompare() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpCompare = null;


        try {
            // InternalLang.g:2131:49: (iv_ruleOpCompare= ruleOpCompare EOF )
            // InternalLang.g:2132:2: iv_ruleOpCompare= ruleOpCompare EOF
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
    // InternalLang.g:2138:1: ruleOpCompare returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) ;
    public final AntlrDatatypeRuleToken ruleOpCompare() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalLang.g:2144:2: ( (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' ) )
            // InternalLang.g:2145:2: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            {
            // InternalLang.g:2145:2: (kw= '>=' | kw= '<=' | kw= '>' | kw= '<' )
            int alt44=4;
            switch ( input.LA(1) ) {
            case 40:
                {
                alt44=1;
                }
                break;
            case 41:
                {
                alt44=2;
                }
                break;
            case 25:
                {
                alt44=3;
                }
                break;
            case 23:
                {
                alt44=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // InternalLang.g:2146:3: kw= '>='
                    {
                    kw=(Token)match(input,40,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignEqualsSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalLang.g:2152:3: kw= '<='
                    {
                    kw=(Token)match(input,41,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignEqualsSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalLang.g:2158:3: kw= '>'
                    {
                    kw=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalLang.g:2164:3: kw= '<'
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
    // InternalLang.g:2173:1: entryRuleYAdditiveExpression returns [EObject current=null] : iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF ;
    public final EObject entryRuleYAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAdditiveExpression = null;


        try {
            // InternalLang.g:2173:60: (iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF )
            // InternalLang.g:2174:2: iv_ruleYAdditiveExpression= ruleYAdditiveExpression EOF
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
    // InternalLang.g:2180:1: ruleYAdditiveExpression returns [EObject current=null] : (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleYAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_YMultiplicativeExpression_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2186:2: ( (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* ) )
            // InternalLang.g:2187:2: (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* )
            {
            // InternalLang.g:2187:2: (this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )* )
            // InternalLang.g:2188:3: this_YMultiplicativeExpression_0= ruleYMultiplicativeExpression ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYAdditiveExpressionAccess().getYMultiplicativeExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_45);
            this_YMultiplicativeExpression_0=ruleYMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YMultiplicativeExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2196:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=42 && LA46_0<=43)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // InternalLang.g:2197:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleYMultiplicativeExpression ) )
            	    {
            	    // InternalLang.g:2197:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt45=2;
            	    int LA45_0 = input.LA(1);

            	    if ( (LA45_0==42) ) {
            	        alt45=1;
            	    }
            	    else if ( (LA45_0==43) ) {
            	        alt45=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 45, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt45) {
            	        case 1 :
            	            // InternalLang.g:2198:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalLang.g:2198:5: ( () otherlv_2= '+' )
            	            // InternalLang.g:2199:6: () otherlv_2= '+'
            	            {
            	            // InternalLang.g:2199:6: ()
            	            // InternalLang.g:2200:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getYAdditiveExpressionAccess().getYPlusLeftAction_1_0_0_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_2=(Token)match(input,42,FOLLOW_31); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_2, grammarAccess.getYAdditiveExpressionAccess().getPlusSignKeyword_1_0_0_1());
            	              					
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalLang.g:2212:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalLang.g:2212:5: ( () otherlv_4= '-' )
            	            // InternalLang.g:2213:6: () otherlv_4= '-'
            	            {
            	            // InternalLang.g:2213:6: ()
            	            // InternalLang.g:2214:7: 
            	            {
            	            if ( state.backtracking==0 ) {

            	              							current = forceCreateModelElementAndSet(
            	              								grammarAccess.getYAdditiveExpressionAccess().getYMinusLeftAction_1_0_1_0(),
            	              								current);
            	              						
            	            }

            	            }

            	            otherlv_4=(Token)match(input,43,FOLLOW_31); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_4, grammarAccess.getYAdditiveExpressionAccess().getHyphenMinusKeyword_1_0_1_1());
            	              					
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // InternalLang.g:2226:4: ( (lv_right_5_0= ruleYMultiplicativeExpression ) )
            	    // InternalLang.g:2227:5: (lv_right_5_0= ruleYMultiplicativeExpression )
            	    {
            	    // InternalLang.g:2227:5: (lv_right_5_0= ruleYMultiplicativeExpression )
            	    // InternalLang.g:2228:6: lv_right_5_0= ruleYMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAdditiveExpressionAccess().getRightYMultiplicativeExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_45);
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
            	    break loop46;
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
    // InternalLang.g:2250:1: entryRuleYMultiplicativeExpression returns [EObject current=null] : iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF ;
    public final EObject entryRuleYMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYMultiplicativeExpression = null;


        try {
            // InternalLang.g:2250:66: (iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF )
            // InternalLang.g:2251:2: iv_ruleYMultiplicativeExpression= ruleYMultiplicativeExpression EOF
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
    // InternalLang.g:2257:1: ruleYMultiplicativeExpression returns [EObject current=null] : (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* ) ;
    public final EObject ruleYMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_YPrimary_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2263:2: ( (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* ) )
            // InternalLang.g:2264:2: (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* )
            {
            // InternalLang.g:2264:2: (this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )* )
            // InternalLang.g:2265:3: this_YPrimary_0= ruleYPrimary ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getYMultiplicativeExpressionAccess().getYPrimaryParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_46);
            this_YPrimary_0=ruleYPrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_YPrimary_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalLang.g:2273:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=44 && LA48_0<=45)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalLang.g:2274:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleYPrimary ) )
            	    {
            	    // InternalLang.g:2274:4: ()
            	    // InternalLang.g:2275:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getYMultiplicativeExpressionAccess().getYMulOrDivLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalLang.g:2281:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalLang.g:2282:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalLang.g:2282:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalLang.g:2283:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalLang.g:2283:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( (LA47_0==44) ) {
            	        alt47=1;
            	    }
            	    else if ( (LA47_0==45) ) {
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
            	            // InternalLang.g:2284:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,44,FOLLOW_31); if (state.failed) return current;
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
            	            // InternalLang.g:2295:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,45,FOLLOW_31); if (state.failed) return current;
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

            	    // InternalLang.g:2308:4: ( (lv_right_3_0= ruleYPrimary ) )
            	    // InternalLang.g:2309:5: (lv_right_3_0= ruleYPrimary )
            	    {
            	    // InternalLang.g:2309:5: (lv_right_3_0= ruleYPrimary )
            	    // InternalLang.g:2310:6: lv_right_3_0= ruleYPrimary
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYMultiplicativeExpressionAccess().getRightYPrimaryParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_46);
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
    // $ANTLR end "ruleYMultiplicativeExpression"


    // $ANTLR start "entryRuleYPrimary"
    // InternalLang.g:2332:1: entryRuleYPrimary returns [EObject current=null] : iv_ruleYPrimary= ruleYPrimary EOF ;
    public final EObject entryRuleYPrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYPrimary = null;


        try {
            // InternalLang.g:2332:49: (iv_ruleYPrimary= ruleYPrimary EOF )
            // InternalLang.g:2333:2: iv_ruleYPrimary= ruleYPrimary EOF
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
    // InternalLang.g:2339:1: ruleYPrimary returns [EObject current=null] : ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression ) ;
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
            // InternalLang.g:2345:2: ( ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression ) )
            // InternalLang.g:2346:2: ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression )
            {
            // InternalLang.g:2346:2: ( ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' ) | ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) ) | this_YSelectionExpression_7= ruleYSelectionExpression )
            int alt49=3;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt49=1;
                }
                break;
            case 46:
                {
                alt49=2;
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
                alt49=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // InternalLang.g:2347:3: ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' )
                    {
                    // InternalLang.g:2347:3: ( () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')' )
                    // InternalLang.g:2348:4: () otherlv_1= '(' ( (lv_a_2_0= ruleYOrExpression ) ) otherlv_3= ')'
                    {
                    // InternalLang.g:2348:4: ()
                    // InternalLang.g:2349:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYPrimaryAccess().getYParentiesAction_0_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_1=(Token)match(input,27,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getYPrimaryAccess().getLeftParenthesisKeyword_0_1());
                      			
                    }
                    // InternalLang.g:2359:4: ( (lv_a_2_0= ruleYOrExpression ) )
                    // InternalLang.g:2360:5: (lv_a_2_0= ruleYOrExpression )
                    {
                    // InternalLang.g:2360:5: (lv_a_2_0= ruleYOrExpression )
                    // InternalLang.g:2361:6: lv_a_2_0= ruleYOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYPrimaryAccess().getAYOrExpressionParserRuleCall_0_2_0());
                      					
                    }
                    pushFollow(FOLLOW_33);
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
                    // InternalLang.g:2384:3: ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) )
                    {
                    // InternalLang.g:2384:3: ( () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) ) )
                    // InternalLang.g:2385:4: () otherlv_5= '!' ( (lv_expression_6_0= ruleYPrimary ) )
                    {
                    // InternalLang.g:2385:4: ()
                    // InternalLang.g:2386:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYPrimaryAccess().getYNotAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_5=(Token)match(input,46,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getYPrimaryAccess().getExclamationMarkKeyword_1_1());
                      			
                    }
                    // InternalLang.g:2396:4: ( (lv_expression_6_0= ruleYPrimary ) )
                    // InternalLang.g:2397:5: (lv_expression_6_0= ruleYPrimary )
                    {
                    // InternalLang.g:2397:5: (lv_expression_6_0= ruleYPrimary )
                    // InternalLang.g:2398:6: lv_expression_6_0= ruleYPrimary
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
                    // InternalLang.g:2417:3: this_YSelectionExpression_7= ruleYSelectionExpression
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
    // InternalLang.g:2429:1: entryRuleYTerminalExpression returns [EObject current=null] : iv_ruleYTerminalExpression= ruleYTerminalExpression EOF ;
    public final EObject entryRuleYTerminalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYTerminalExpression = null;


        try {
            // InternalLang.g:2429:60: (iv_ruleYTerminalExpression= ruleYTerminalExpression EOF )
            // InternalLang.g:2430:2: iv_ruleYTerminalExpression= ruleYTerminalExpression EOF
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
    // InternalLang.g:2436:1: ruleYTerminalExpression returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) ) ;
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
            // InternalLang.g:2442:2: ( ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) ) )
            // InternalLang.g:2443:2: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) )
            {
            // InternalLang.g:2443:2: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) ) | ( () otherlv_7= 'self' ) | ( () otherlv_9= 'super' ) | ( () otherlv_11= 'null' ) | ( () ( (otherlv_13= RULE_ID ) ) ) | ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' ) )
            int alt53=8;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt53=1;
                }
                break;
            case RULE_INT:
                {
                alt53=2;
                }
                break;
            case 47:
            case 48:
                {
                alt53=3;
                }
                break;
            case 49:
                {
                alt53=4;
                }
                break;
            case 50:
                {
                alt53=5;
                }
                break;
            case 51:
                {
                alt53=6;
                }
                break;
            case RULE_ID:
                {
                alt53=7;
                }
                break;
            case 52:
                {
                alt53=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // InternalLang.g:2444:3: ( () ( (lv_value_1_0= RULE_STRING ) ) )
                    {
                    // InternalLang.g:2444:3: ( () ( (lv_value_1_0= RULE_STRING ) ) )
                    // InternalLang.g:2445:4: () ( (lv_value_1_0= RULE_STRING ) )
                    {
                    // InternalLang.g:2445:4: ()
                    // InternalLang.g:2446:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYStringConstantAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2452:4: ( (lv_value_1_0= RULE_STRING ) )
                    // InternalLang.g:2453:5: (lv_value_1_0= RULE_STRING )
                    {
                    // InternalLang.g:2453:5: (lv_value_1_0= RULE_STRING )
                    // InternalLang.g:2454:6: lv_value_1_0= RULE_STRING
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
                    // InternalLang.g:2472:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalLang.g:2472:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalLang.g:2473:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalLang.g:2473:4: ()
                    // InternalLang.g:2474:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYIntConstantAction_1_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2480:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalLang.g:2481:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalLang.g:2481:5: (lv_value_3_0= RULE_INT )
                    // InternalLang.g:2482:6: lv_value_3_0= RULE_INT
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
                    // InternalLang.g:2500:3: ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) )
                    {
                    // InternalLang.g:2500:3: ( () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) ) )
                    // InternalLang.g:2501:4: () ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) )
                    {
                    // InternalLang.g:2501:4: ()
                    // InternalLang.g:2502:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYBoolConstantAction_2_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2508:4: ( ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) ) )
                    // InternalLang.g:2509:5: ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) )
                    {
                    // InternalLang.g:2509:5: ( (lv_value_5_1= 'true' | lv_value_5_2= 'false' ) )
                    // InternalLang.g:2510:6: (lv_value_5_1= 'true' | lv_value_5_2= 'false' )
                    {
                    // InternalLang.g:2510:6: (lv_value_5_1= 'true' | lv_value_5_2= 'false' )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==47) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==48) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // InternalLang.g:2511:7: lv_value_5_1= 'true'
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
                            // InternalLang.g:2522:7: lv_value_5_2= 'false'
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
                    // InternalLang.g:2537:3: ( () otherlv_7= 'self' )
                    {
                    // InternalLang.g:2537:3: ( () otherlv_7= 'self' )
                    // InternalLang.g:2538:4: () otherlv_7= 'self'
                    {
                    // InternalLang.g:2538:4: ()
                    // InternalLang.g:2539:5: 
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
                    // InternalLang.g:2551:3: ( () otherlv_9= 'super' )
                    {
                    // InternalLang.g:2551:3: ( () otherlv_9= 'super' )
                    // InternalLang.g:2552:4: () otherlv_9= 'super'
                    {
                    // InternalLang.g:2552:4: ()
                    // InternalLang.g:2553:5: 
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
                    // InternalLang.g:2565:3: ( () otherlv_11= 'null' )
                    {
                    // InternalLang.g:2565:3: ( () otherlv_11= 'null' )
                    // InternalLang.g:2566:4: () otherlv_11= 'null'
                    {
                    // InternalLang.g:2566:4: ()
                    // InternalLang.g:2567:5: 
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
                    // InternalLang.g:2579:3: ( () ( (otherlv_13= RULE_ID ) ) )
                    {
                    // InternalLang.g:2579:3: ( () ( (otherlv_13= RULE_ID ) ) )
                    // InternalLang.g:2580:4: () ( (otherlv_13= RULE_ID ) )
                    {
                    // InternalLang.g:2580:4: ()
                    // InternalLang.g:2581:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getYTerminalExpressionAccess().getYSymbolRefAction_6_0(),
                      						current);
                      				
                    }

                    }

                    // InternalLang.g:2587:4: ( (otherlv_13= RULE_ID ) )
                    // InternalLang.g:2588:5: (otherlv_13= RULE_ID )
                    {
                    // InternalLang.g:2588:5: (otherlv_13= RULE_ID )
                    // InternalLang.g:2589:6: otherlv_13= RULE_ID
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
                    // InternalLang.g:2602:3: ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' )
                    {
                    // InternalLang.g:2602:3: ( () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')' )
                    // InternalLang.g:2603:4: () otherlv_15= 'new' ( ( ruleQualifiedName ) ) otherlv_17= '(' ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )? otherlv_21= ')'
                    {
                    // InternalLang.g:2603:4: ()
                    // InternalLang.g:2604:5: 
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
                    // InternalLang.g:2614:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:2615:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:2615:5: ( ruleQualifiedName )
                    // InternalLang.g:2616:6: ruleQualifiedName
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

                    otherlv_17=(Token)match(input,27,FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_17, grammarAccess.getYTerminalExpressionAccess().getLeftParenthesisKeyword_7_3());
                      			
                    }
                    // InternalLang.g:2634:4: ( ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )* )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( ((LA52_0>=RULE_ID && LA52_0<=RULE_STRING)||LA52_0==27||(LA52_0>=46 && LA52_0<=52)) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // InternalLang.g:2635:5: ( (lv_arguments_18_0= ruleYOrExpression ) ) (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )*
                            {
                            // InternalLang.g:2635:5: ( (lv_arguments_18_0= ruleYOrExpression ) )
                            // InternalLang.g:2636:6: (lv_arguments_18_0= ruleYOrExpression )
                            {
                            // InternalLang.g:2636:6: (lv_arguments_18_0= ruleYOrExpression )
                            // InternalLang.g:2637:7: lv_arguments_18_0= ruleYOrExpression
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

                            // InternalLang.g:2654:5: (otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) ) )*
                            loop51:
                            do {
                                int alt51=2;
                                int LA51_0 = input.LA(1);

                                if ( (LA51_0==24) ) {
                                    alt51=1;
                                }


                                switch (alt51) {
                            	case 1 :
                            	    // InternalLang.g:2655:6: otherlv_19= ',' ( (lv_arguments_20_0= ruleYOrExpression ) )
                            	    {
                            	    otherlv_19=(Token)match(input,24,FOLLOW_31); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_19, grammarAccess.getYTerminalExpressionAccess().getCommaKeyword_7_4_1_0());
                            	      					
                            	    }
                            	    // InternalLang.g:2659:6: ( (lv_arguments_20_0= ruleYOrExpression ) )
                            	    // InternalLang.g:2660:7: (lv_arguments_20_0= ruleYOrExpression )
                            	    {
                            	    // InternalLang.g:2660:7: (lv_arguments_20_0= ruleYOrExpression )
                            	    // InternalLang.g:2661:8: lv_arguments_20_0= ruleYOrExpression
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
                            	    break loop51;
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
    // InternalLang.g:2689:1: entryRuleYReadStatement returns [EObject current=null] : iv_ruleYReadStatement= ruleYReadStatement EOF ;
    public final EObject entryRuleYReadStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReadStatement = null;


        try {
            // InternalLang.g:2689:55: (iv_ruleYReadStatement= ruleYReadStatement EOF )
            // InternalLang.g:2690:2: iv_ruleYReadStatement= ruleYReadStatement EOF
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
    // InternalLang.g:2696:1: ruleYReadStatement returns [EObject current=null] : (otherlv_0= 'read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) ) ;
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
            // InternalLang.g:2702:2: ( (otherlv_0= 'read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) ) )
            // InternalLang.g:2703:2: (otherlv_0= 'read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) )
            {
            // InternalLang.g:2703:2: (otherlv_0= 'read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) ) )
            // InternalLang.g:2704:3: otherlv_0= 'read' ( (lv_structs_1_0= ruleYStructRefPair ) ) (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_4_0= ruleYJoin ) )? ( (lv_whereclause_5_0= ruleYWhere ) )? otherlv_6= 'success' ( (lv_success_7_0= ruleYBlock ) ) otherlv_8= 'not' otherlv_9= 'found' ( (lv_notfound_10_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYReadStatementAccess().getReadKeyword_0());
              		
            }
            // InternalLang.g:2708:3: ( (lv_structs_1_0= ruleYStructRefPair ) )
            // InternalLang.g:2709:4: (lv_structs_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:2709:4: (lv_structs_1_0= ruleYStructRefPair )
            // InternalLang.g:2710:5: lv_structs_1_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadStatementAccess().getStructsYStructRefPairParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_47);
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

            // InternalLang.g:2727:3: (otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) ) )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==24) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // InternalLang.g:2728:4: otherlv_2= ',' ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    {
            	    otherlv_2=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getYReadStatementAccess().getCommaKeyword_2_0());
            	      			
            	    }
            	    // InternalLang.g:2732:4: ( (lv_structs_3_0= ruleYStructRefPair ) )
            	    // InternalLang.g:2733:5: (lv_structs_3_0= ruleYStructRefPair )
            	    {
            	    // InternalLang.g:2733:5: (lv_structs_3_0= ruleYStructRefPair )
            	    // InternalLang.g:2734:6: lv_structs_3_0= ruleYStructRefPair
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYReadStatementAccess().getStructsYStructRefPairParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_47);
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
            	    break loop54;
                }
            } while (true);

            // InternalLang.g:2752:3: ( (lv_joinclause_4_0= ruleYJoin ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==65) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalLang.g:2753:4: (lv_joinclause_4_0= ruleYJoin )
                    {
                    // InternalLang.g:2753:4: (lv_joinclause_4_0= ruleYJoin )
                    // InternalLang.g:2754:5: lv_joinclause_4_0= ruleYJoin
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadStatementAccess().getJoinclauseYJoinParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_48);
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

            // InternalLang.g:2771:3: ( (lv_whereclause_5_0= ruleYWhere ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==66) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalLang.g:2772:4: (lv_whereclause_5_0= ruleYWhere )
                    {
                    // InternalLang.g:2772:4: (lv_whereclause_5_0= ruleYWhere )
                    // InternalLang.g:2773:5: lv_whereclause_5_0= ruleYWhere
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadStatementAccess().getWhereclauseYWhereParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_49);
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
            // InternalLang.g:2794:3: ( (lv_success_7_0= ruleYBlock ) )
            // InternalLang.g:2795:4: (lv_success_7_0= ruleYBlock )
            {
            // InternalLang.g:2795:4: (lv_success_7_0= ruleYBlock )
            // InternalLang.g:2796:5: lv_success_7_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadStatementAccess().getSuccessYBlockParserRuleCall_6_0());
              				
            }
            pushFollow(FOLLOW_50);
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

            otherlv_8=(Token)match(input,55,FOLLOW_51); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getYReadStatementAccess().getNotKeyword_7());
              		
            }
            otherlv_9=(Token)match(input,56,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_9, grammarAccess.getYReadStatementAccess().getFoundKeyword_8());
              		
            }
            // InternalLang.g:2821:3: ( (lv_notfound_10_0= ruleYBlock ) )
            // InternalLang.g:2822:4: (lv_notfound_10_0= ruleYBlock )
            {
            // InternalLang.g:2822:4: (lv_notfound_10_0= ruleYBlock )
            // InternalLang.g:2823:5: lv_notfound_10_0= ruleYBlock
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
    // InternalLang.g:2844:1: entryRuleYReadEachStatement returns [EObject current=null] : iv_ruleYReadEachStatement= ruleYReadEachStatement EOF ;
    public final EObject entryRuleYReadEachStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYReadEachStatement = null;


        try {
            // InternalLang.g:2844:59: (iv_ruleYReadEachStatement= ruleYReadEachStatement EOF )
            // InternalLang.g:2845:2: iv_ruleYReadEachStatement= ruleYReadEachStatement EOF
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
    // InternalLang.g:2851:1: ruleYReadEachStatement returns [EObject current=null] : (otherlv_0= 'read' otherlv_1= 'each' ( (lv_structs_2_0= ruleYStructRefPair ) ) (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_5_0= ruleYJoin ) )? ( (lv_whereclause_6_0= ruleYWhere ) )? otherlv_7= 'target' ( (otherlv_8= RULE_ID ) ) ( (lv_success_9_0= ruleYBlock ) ) ) ;
    public final EObject ruleYReadEachStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_structs_2_0 = null;

        EObject lv_structs_4_0 = null;

        EObject lv_joinclause_5_0 = null;

        EObject lv_whereclause_6_0 = null;

        EObject lv_success_9_0 = null;



        	enterRule();

        try {
            // InternalLang.g:2857:2: ( (otherlv_0= 'read' otherlv_1= 'each' ( (lv_structs_2_0= ruleYStructRefPair ) ) (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_5_0= ruleYJoin ) )? ( (lv_whereclause_6_0= ruleYWhere ) )? otherlv_7= 'target' ( (otherlv_8= RULE_ID ) ) ( (lv_success_9_0= ruleYBlock ) ) ) )
            // InternalLang.g:2858:2: (otherlv_0= 'read' otherlv_1= 'each' ( (lv_structs_2_0= ruleYStructRefPair ) ) (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_5_0= ruleYJoin ) )? ( (lv_whereclause_6_0= ruleYWhere ) )? otherlv_7= 'target' ( (otherlv_8= RULE_ID ) ) ( (lv_success_9_0= ruleYBlock ) ) )
            {
            // InternalLang.g:2858:2: (otherlv_0= 'read' otherlv_1= 'each' ( (lv_structs_2_0= ruleYStructRefPair ) ) (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_5_0= ruleYJoin ) )? ( (lv_whereclause_6_0= ruleYWhere ) )? otherlv_7= 'target' ( (otherlv_8= RULE_ID ) ) ( (lv_success_9_0= ruleYBlock ) ) )
            // InternalLang.g:2859:3: otherlv_0= 'read' otherlv_1= 'each' ( (lv_structs_2_0= ruleYStructRefPair ) ) (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )* ( (lv_joinclause_5_0= ruleYJoin ) )? ( (lv_whereclause_6_0= ruleYWhere ) )? otherlv_7= 'target' ( (otherlv_8= RULE_ID ) ) ( (lv_success_9_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,53,FOLLOW_52); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYReadEachStatementAccess().getReadKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,57,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYReadEachStatementAccess().getEachKeyword_1());
              		
            }
            // InternalLang.g:2867:3: ( (lv_structs_2_0= ruleYStructRefPair ) )
            // InternalLang.g:2868:4: (lv_structs_2_0= ruleYStructRefPair )
            {
            // InternalLang.g:2868:4: (lv_structs_2_0= ruleYStructRefPair )
            // InternalLang.g:2869:5: lv_structs_2_0= ruleYStructRefPair
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getStructsYStructRefPairParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_53);
            lv_structs_2_0=ruleYStructRefPair();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
              					}
              					add(
              						current,
              						"structs",
              						lv_structs_2_0,
              						"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:2886:3: (otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) ) )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==24) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalLang.g:2887:4: otherlv_3= ',' ( (lv_structs_4_0= ruleYStructRefPair ) )
            	    {
            	    otherlv_3=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getYReadEachStatementAccess().getCommaKeyword_3_0());
            	      			
            	    }
            	    // InternalLang.g:2891:4: ( (lv_structs_4_0= ruleYStructRefPair ) )
            	    // InternalLang.g:2892:5: (lv_structs_4_0= ruleYStructRefPair )
            	    {
            	    // InternalLang.g:2892:5: (lv_structs_4_0= ruleYStructRefPair )
            	    // InternalLang.g:2893:6: lv_structs_4_0= ruleYStructRefPair
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYReadEachStatementAccess().getStructsYStructRefPairParserRuleCall_3_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_53);
            	    lv_structs_4_0=ruleYStructRefPair();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
            	      						}
            	      						add(
            	      							current,
            	      							"structs",
            	      							lv_structs_4_0,
            	      							"eu.jgen.notes.dmw.lite.Lang.YStructRefPair");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

            // InternalLang.g:2911:3: ( (lv_joinclause_5_0= ruleYJoin ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==65) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalLang.g:2912:4: (lv_joinclause_5_0= ruleYJoin )
                    {
                    // InternalLang.g:2912:4: (lv_joinclause_5_0= ruleYJoin )
                    // InternalLang.g:2913:5: lv_joinclause_5_0= ruleYJoin
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getJoinclauseYJoinParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_54);
                    lv_joinclause_5_0=ruleYJoin();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
                      					}
                      					set(
                      						current,
                      						"joinclause",
                      						lv_joinclause_5_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YJoin");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalLang.g:2930:3: ( (lv_whereclause_6_0= ruleYWhere ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==66) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalLang.g:2931:4: (lv_whereclause_6_0= ruleYWhere )
                    {
                    // InternalLang.g:2931:4: (lv_whereclause_6_0= ruleYWhere )
                    // InternalLang.g:2932:5: lv_whereclause_6_0= ruleYWhere
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getWhereclauseYWhereParserRuleCall_5_0());
                      				
                    }
                    pushFollow(FOLLOW_55);
                    lv_whereclause_6_0=ruleYWhere();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
                      					}
                      					set(
                      						current,
                      						"whereclause",
                      						lv_whereclause_6_0,
                      						"eu.jgen.notes.dmw.lite.Lang.YWhere");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,58,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYReadEachStatementAccess().getTargetKeyword_6());
              		
            }
            // InternalLang.g:2953:3: ( (otherlv_8= RULE_ID ) )
            // InternalLang.g:2954:4: (otherlv_8= RULE_ID )
            {
            // InternalLang.g:2954:4: (otherlv_8= RULE_ID )
            // InternalLang.g:2955:5: otherlv_8= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYReadEachStatementRule());
              					}
              				
            }
            otherlv_8=(Token)match(input,RULE_ID,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_8, grammarAccess.getYReadEachStatementAccess().getTargetYPropertyCrossReference_7_0());
              				
            }

            }


            }

            // InternalLang.g:2966:3: ( (lv_success_9_0= ruleYBlock ) )
            // InternalLang.g:2967:4: (lv_success_9_0= ruleYBlock )
            {
            // InternalLang.g:2967:4: (lv_success_9_0= ruleYBlock )
            // InternalLang.g:2968:5: lv_success_9_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYReadEachStatementAccess().getSuccessYBlockParserRuleCall_8_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_success_9_0=ruleYBlock();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYReadEachStatementRule());
              					}
              					set(
              						current,
              						"success",
              						lv_success_9_0,
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
    // InternalLang.g:2989:1: entryRuleYCreateStatement returns [EObject current=null] : iv_ruleYCreateStatement= ruleYCreateStatement EOF ;
    public final EObject entryRuleYCreateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYCreateStatement = null;


        try {
            // InternalLang.g:2989:57: (iv_ruleYCreateStatement= ruleYCreateStatement EOF )
            // InternalLang.g:2990:2: iv_ruleYCreateStatement= ruleYCreateStatement EOF
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
    // InternalLang.g:2996:1: ruleYCreateStatement returns [EObject current=null] : (otherlv_0= 'create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) ) ;
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
            // InternalLang.g:3002:2: ( (otherlv_0= 'create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) ) )
            // InternalLang.g:3003:2: (otherlv_0= 'create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3003:2: (otherlv_0= 'create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) ) )
            // InternalLang.g:3004:3: otherlv_0= 'create' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) otherlv_5= 'already' otherlv_6= 'exist' ( (lv_alreadyExist_7_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,59,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYCreateStatementAccess().getCreateKeyword_0());
              		
            }
            // InternalLang.g:3008:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3009:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3009:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3010:5: lv_struct_1_0= ruleYStructRefPair
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

            // InternalLang.g:3027:3: ( (lv_setBlock_2_0= ruleYBlock ) )
            // InternalLang.g:3028:4: (lv_setBlock_2_0= ruleYBlock )
            {
            // InternalLang.g:3028:4: (lv_setBlock_2_0= ruleYBlock )
            // InternalLang.g:3029:5: lv_setBlock_2_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYCreateStatementAccess().getSetBlockYBlockParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_49);
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
            // InternalLang.g:3050:3: ( (lv_success_4_0= ruleYBlock ) )
            // InternalLang.g:3051:4: (lv_success_4_0= ruleYBlock )
            {
            // InternalLang.g:3051:4: (lv_success_4_0= ruleYBlock )
            // InternalLang.g:3052:5: lv_success_4_0= ruleYBlock
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
            // InternalLang.g:3077:3: ( (lv_alreadyExist_7_0= ruleYBlock ) )
            // InternalLang.g:3078:4: (lv_alreadyExist_7_0= ruleYBlock )
            {
            // InternalLang.g:3078:4: (lv_alreadyExist_7_0= ruleYBlock )
            // InternalLang.g:3079:5: lv_alreadyExist_7_0= ruleYBlock
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
    // InternalLang.g:3100:1: entryRuleYUpdateStatement returns [EObject current=null] : iv_ruleYUpdateStatement= ruleYUpdateStatement EOF ;
    public final EObject entryRuleYUpdateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYUpdateStatement = null;


        try {
            // InternalLang.g:3100:57: (iv_ruleYUpdateStatement= ruleYUpdateStatement EOF )
            // InternalLang.g:3101:2: iv_ruleYUpdateStatement= ruleYUpdateStatement EOF
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
    // InternalLang.g:3107:1: ruleYUpdateStatement returns [EObject current=null] : (otherlv_0= 'update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYUpdateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_struct_1_0 = null;

        EObject lv_setBlock_2_0 = null;

        EObject lv_success_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3113:2: ( (otherlv_0= 'update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3114:2: (otherlv_0= 'update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3114:2: (otherlv_0= 'update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) ) )
            // InternalLang.g:3115:3: otherlv_0= 'update' ( (lv_struct_1_0= ruleYStructRefPair ) ) ( (lv_setBlock_2_0= ruleYBlock ) ) otherlv_3= 'success' ( (lv_success_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,62,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYUpdateStatementAccess().getUpdateKeyword_0());
              		
            }
            // InternalLang.g:3119:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3120:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3120:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3121:5: lv_struct_1_0= ruleYStructRefPair
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

            // InternalLang.g:3138:3: ( (lv_setBlock_2_0= ruleYBlock ) )
            // InternalLang.g:3139:4: (lv_setBlock_2_0= ruleYBlock )
            {
            // InternalLang.g:3139:4: (lv_setBlock_2_0= ruleYBlock )
            // InternalLang.g:3140:5: lv_setBlock_2_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYUpdateStatementAccess().getSetBlockYBlockParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_49);
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
            // InternalLang.g:3161:3: ( (lv_success_4_0= ruleYBlock ) )
            // InternalLang.g:3162:4: (lv_success_4_0= ruleYBlock )
            {
            // InternalLang.g:3162:4: (lv_success_4_0= ruleYBlock )
            // InternalLang.g:3163:5: lv_success_4_0= ruleYBlock
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
    // InternalLang.g:3184:1: entryRuleYDeleteStatement returns [EObject current=null] : iv_ruleYDeleteStatement= ruleYDeleteStatement EOF ;
    public final EObject entryRuleYDeleteStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYDeleteStatement = null;


        try {
            // InternalLang.g:3184:57: (iv_ruleYDeleteStatement= ruleYDeleteStatement EOF )
            // InternalLang.g:3185:2: iv_ruleYDeleteStatement= ruleYDeleteStatement EOF
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
    // InternalLang.g:3191:1: ruleYDeleteStatement returns [EObject current=null] : (otherlv_0= 'delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' ) ;
    public final EObject ruleYDeleteStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_struct_1_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3197:2: ( (otherlv_0= 'delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' ) )
            // InternalLang.g:3198:2: (otherlv_0= 'delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' )
            {
            // InternalLang.g:3198:2: (otherlv_0= 'delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';' )
            // InternalLang.g:3199:3: otherlv_0= 'delete' ( (lv_struct_1_0= ruleYStructRefPair ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,63,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYDeleteStatementAccess().getDeleteKeyword_0());
              		
            }
            // InternalLang.g:3203:3: ( (lv_struct_1_0= ruleYStructRefPair ) )
            // InternalLang.g:3204:4: (lv_struct_1_0= ruleYStructRefPair )
            {
            // InternalLang.g:3204:4: (lv_struct_1_0= ruleYStructRefPair )
            // InternalLang.g:3205:5: lv_struct_1_0= ruleYStructRefPair
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
    // InternalLang.g:3230:1: entryRuleYAssociateStatement returns [EObject current=null] : iv_ruleYAssociateStatement= ruleYAssociateStatement EOF ;
    public final EObject entryRuleYAssociateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAssociateStatement = null;


        try {
            // InternalLang.g:3230:60: (iv_ruleYAssociateStatement= ruleYAssociateStatement EOF )
            // InternalLang.g:3231:2: iv_ruleYAssociateStatement= ruleYAssociateStatement EOF
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
    // InternalLang.g:3237:1: ruleYAssociateStatement returns [EObject current=null] : (otherlv_0= 'associate' ( (lv_joinref_1_0= ruleYJoinDef ) ) otherlv_2= ';' ) ;
    public final EObject ruleYAssociateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_joinref_1_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3243:2: ( (otherlv_0= 'associate' ( (lv_joinref_1_0= ruleYJoinDef ) ) otherlv_2= ';' ) )
            // InternalLang.g:3244:2: (otherlv_0= 'associate' ( (lv_joinref_1_0= ruleYJoinDef ) ) otherlv_2= ';' )
            {
            // InternalLang.g:3244:2: (otherlv_0= 'associate' ( (lv_joinref_1_0= ruleYJoinDef ) ) otherlv_2= ';' )
            // InternalLang.g:3245:3: otherlv_0= 'associate' ( (lv_joinref_1_0= ruleYJoinDef ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAssociateStatementAccess().getAssociateKeyword_0());
              		
            }
            // InternalLang.g:3249:3: ( (lv_joinref_1_0= ruleYJoinDef ) )
            // InternalLang.g:3250:4: (lv_joinref_1_0= ruleYJoinDef )
            {
            // InternalLang.g:3250:4: (lv_joinref_1_0= ruleYJoinDef )
            // InternalLang.g:3251:5: lv_joinref_1_0= ruleYJoinDef
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAssociateStatementAccess().getJoinrefYJoinDefParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_joinref_1_0=ruleYJoinDef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getYAssociateStatementRule());
              					}
              					set(
              						current,
              						"joinref",
              						lv_joinref_1_0,
              						"eu.jgen.notes.dmw.lite.Lang.YJoinDef");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAssociateStatementAccess().getSemicolonKeyword_2());
              		
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


    // $ANTLR start "entryRuleYStructRefPair"
    // InternalLang.g:3276:1: entryRuleYStructRefPair returns [EObject current=null] : iv_ruleYStructRefPair= ruleYStructRefPair EOF ;
    public final EObject entryRuleYStructRefPair() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYStructRefPair = null;


        try {
            // InternalLang.g:3276:55: (iv_ruleYStructRefPair= ruleYStructRefPair EOF )
            // InternalLang.g:3277:2: iv_ruleYStructRefPair= ruleYStructRefPair EOF
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
    // InternalLang.g:3283:1: ruleYStructRefPair returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleYStructRefPair() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalLang.g:3289:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) ) )
            // InternalLang.g:3290:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalLang.g:3290:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) )
            // InternalLang.g:3291:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) )
            {
            // InternalLang.g:3291:3: ( (otherlv_0= RULE_ID ) )
            // InternalLang.g:3292:4: (otherlv_0= RULE_ID )
            {
            // InternalLang.g:3292:4: (otherlv_0= RULE_ID )
            // InternalLang.g:3293:5: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYStructRefPairRule());
              					}
              				
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_58); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_0, grammarAccess.getYStructRefPairAccess().getStructpropertyYPropertyCrossReference_0_0());
              				
            }

            }


            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYStructRefPairAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:3308:3: ( (otherlv_2= RULE_ID ) )
            // InternalLang.g:3309:4: (otherlv_2= RULE_ID )
            {
            // InternalLang.g:3309:4: (otherlv_2= RULE_ID )
            // InternalLang.g:3310:5: otherlv_2= RULE_ID
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
    // InternalLang.g:3325:1: entryRuleYJoin returns [EObject current=null] : iv_ruleYJoin= ruleYJoin EOF ;
    public final EObject entryRuleYJoin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYJoin = null;


        try {
            // InternalLang.g:3325:46: (iv_ruleYJoin= ruleYJoin EOF )
            // InternalLang.g:3326:2: iv_ruleYJoin= ruleYJoin EOF
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
    // InternalLang.g:3332:1: ruleYJoin returns [EObject current=null] : ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* ) ;
    public final EObject ruleYJoin() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_joindefs_2_0 = null;

        EObject lv_joindefs_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3338:2: ( ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* ) )
            // InternalLang.g:3339:2: ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* )
            {
            // InternalLang.g:3339:2: ( () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )* )
            // InternalLang.g:3340:3: () otherlv_1= 'join' ( (lv_joindefs_2_0= ruleYJoinDef ) ) (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )*
            {
            // InternalLang.g:3340:3: ()
            // InternalLang.g:3341:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYJoinAccess().getYJoinAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,65,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYJoinAccess().getJoinKeyword_1());
              		
            }
            // InternalLang.g:3351:3: ( (lv_joindefs_2_0= ruleYJoinDef ) )
            // InternalLang.g:3352:4: (lv_joindefs_2_0= ruleYJoinDef )
            {
            // InternalLang.g:3352:4: (lv_joindefs_2_0= ruleYJoinDef )
            // InternalLang.g:3353:5: lv_joindefs_2_0= ruleYJoinDef
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYJoinAccess().getJoindefsYJoinDefParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_59);
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

            // InternalLang.g:3370:3: (otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) ) )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==24) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalLang.g:3371:4: otherlv_3= ',' ( (lv_joindefs_4_0= ruleYJoinDef ) )
            	    {
            	    otherlv_3=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getYJoinAccess().getCommaKeyword_3_0());
            	      			
            	    }
            	    // InternalLang.g:3375:4: ( (lv_joindefs_4_0= ruleYJoinDef ) )
            	    // InternalLang.g:3376:5: (lv_joindefs_4_0= ruleYJoinDef )
            	    {
            	    // InternalLang.g:3376:5: (lv_joindefs_4_0= ruleYJoinDef )
            	    // InternalLang.g:3377:6: lv_joindefs_4_0= ruleYJoinDef
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYJoinAccess().getJoindefsYJoinDefParserRuleCall_3_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_59);
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
            	    break loop60;
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
    // InternalLang.g:3399:1: entryRuleYJoinDef returns [EObject current=null] : iv_ruleYJoinDef= ruleYJoinDef EOF ;
    public final EObject entryRuleYJoinDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYJoinDef = null;


        try {
            // InternalLang.g:3399:49: (iv_ruleYJoinDef= ruleYJoinDef EOF )
            // InternalLang.g:3400:2: iv_ruleYJoinDef= ruleYJoinDef EOF
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
    // InternalLang.g:3406:1: ruleYJoinDef returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) ) ;
    public final EObject ruleYJoinDef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:3412:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) ) )
            // InternalLang.g:3413:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) )
            {
            // InternalLang.g:3413:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) ) )
            // InternalLang.g:3414:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= '->' ( (otherlv_4= RULE_ID ) )
            {
            // InternalLang.g:3414:3: ( (otherlv_0= RULE_ID ) )
            // InternalLang.g:3415:4: (otherlv_0= RULE_ID )
            {
            // InternalLang.g:3415:4: (otherlv_0= RULE_ID )
            // InternalLang.g:3416:5: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYJoinDefRule());
              					}
              				
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_58); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_0, grammarAccess.getYJoinDefAccess().getFromViewYPropertyCrossReference_0_0());
              				
            }

            }


            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYJoinDefAccess().getHyphenMinusGreaterThanSignKeyword_1());
              		
            }
            // InternalLang.g:3431:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:3432:4: ( ruleQualifiedName )
            {
            // InternalLang.g:3432:4: ( ruleQualifiedName )
            // InternalLang.g:3433:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYJoinDefRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYJoinDefAccess().getRelRefYAnnotRelCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_58);
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
            // InternalLang.g:3451:3: ( (otherlv_4= RULE_ID ) )
            // InternalLang.g:3452:4: (otherlv_4= RULE_ID )
            {
            // InternalLang.g:3452:4: (otherlv_4= RULE_ID )
            // InternalLang.g:3453:5: otherlv_4= RULE_ID
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
    // InternalLang.g:3468:1: entryRuleYWhere returns [EObject current=null] : iv_ruleYWhere= ruleYWhere EOF ;
    public final EObject entryRuleYWhere() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYWhere = null;


        try {
            // InternalLang.g:3468:47: (iv_ruleYWhere= ruleYWhere EOF )
            // InternalLang.g:3469:2: iv_ruleYWhere= ruleYWhere EOF
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
    // InternalLang.g:3475:1: ruleYWhere returns [EObject current=null] : ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) ) ;
    public final EObject ruleYWhere() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3481:2: ( ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) ) )
            // InternalLang.g:3482:2: ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) )
            {
            // InternalLang.g:3482:2: ( () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) ) )
            // InternalLang.g:3483:3: () otherlv_1= 'where' ( (lv_expression_2_0= ruleYOrExpression ) )
            {
            // InternalLang.g:3483:3: ()
            // InternalLang.g:3484:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYWhereAccess().getYWhereAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,66,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYWhereAccess().getWhereKeyword_1());
              		
            }
            // InternalLang.g:3494:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:3495:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:3495:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:3496:5: lv_expression_2_0= ruleYOrExpression
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
    // InternalLang.g:3517:1: entryRuleYWhileStatement returns [EObject current=null] : iv_ruleYWhileStatement= ruleYWhileStatement EOF ;
    public final EObject entryRuleYWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYWhileStatement = null;


        try {
            // InternalLang.g:3517:56: (iv_ruleYWhileStatement= ruleYWhileStatement EOF )
            // InternalLang.g:3518:2: iv_ruleYWhileStatement= ruleYWhileStatement EOF
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
    // InternalLang.g:3524:1: ruleYWhileStatement returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_expression_2_0 = null;

        EObject lv_body_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3530:2: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3531:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3531:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) ) )
            // InternalLang.g:3532:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_expression_2_0= ruleYOrExpression ) ) otherlv_3= ')' ( (lv_body_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,67,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYWhileStatementAccess().getWhileKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYWhileStatementAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:3540:3: ( (lv_expression_2_0= ruleYOrExpression ) )
            // InternalLang.g:3541:4: (lv_expression_2_0= ruleYOrExpression )
            {
            // InternalLang.g:3541:4: (lv_expression_2_0= ruleYOrExpression )
            // InternalLang.g:3542:5: lv_expression_2_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYWhileStatementAccess().getExpressionYOrExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_33);
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
            // InternalLang.g:3563:3: ( (lv_body_4_0= ruleYBlock ) )
            // InternalLang.g:3564:4: (lv_body_4_0= ruleYBlock )
            {
            // InternalLang.g:3564:4: (lv_body_4_0= ruleYBlock )
            // InternalLang.g:3565:5: lv_body_4_0= ruleYBlock
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
    // InternalLang.g:3586:1: entryRuleYRepeatWhileStatement returns [EObject current=null] : iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF ;
    public final EObject entryRuleYRepeatWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYRepeatWhileStatement = null;


        try {
            // InternalLang.g:3586:62: (iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF )
            // InternalLang.g:3587:2: iv_ruleYRepeatWhileStatement= ruleYRepeatWhileStatement EOF
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
    // InternalLang.g:3593:1: ruleYRepeatWhileStatement returns [EObject current=null] : (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' ) ;
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
            // InternalLang.g:3599:2: ( (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' ) )
            // InternalLang.g:3600:2: (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' )
            {
            // InternalLang.g:3600:2: (otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')' )
            // InternalLang.g:3601:3: otherlv_0= 'repeat' ( (lv_body_1_0= ruleYBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_expression_4_0= ruleYOrExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,68,FOLLOW_28); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYRepeatWhileStatementAccess().getRepeatKeyword_0());
              		
            }
            // InternalLang.g:3605:3: ( (lv_body_1_0= ruleYBlock ) )
            // InternalLang.g:3606:4: (lv_body_1_0= ruleYBlock )
            {
            // InternalLang.g:3606:4: (lv_body_1_0= ruleYBlock )
            // InternalLang.g:3607:5: lv_body_1_0= ruleYBlock
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYRepeatWhileStatementAccess().getBodyYBlockParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_60);
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

            otherlv_2=(Token)match(input,67,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYRepeatWhileStatementAccess().getWhileKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,27,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYRepeatWhileStatementAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalLang.g:3632:3: ( (lv_expression_4_0= ruleYOrExpression ) )
            // InternalLang.g:3633:4: (lv_expression_4_0= ruleYOrExpression )
            {
            // InternalLang.g:3633:4: (lv_expression_4_0= ruleYOrExpression )
            // InternalLang.g:3634:5: lv_expression_4_0= ruleYOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYRepeatWhileStatementAccess().getExpressionYOrExpressionParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_33);
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
    // InternalLang.g:3659:1: entryRuleYForInStatement returns [EObject current=null] : iv_ruleYForInStatement= ruleYForInStatement EOF ;
    public final EObject entryRuleYForInStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYForInStatement = null;


        try {
            // InternalLang.g:3659:56: (iv_ruleYForInStatement= ruleYForInStatement EOF )
            // InternalLang.g:3660:2: iv_ruleYForInStatement= ruleYForInStatement EOF
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
    // InternalLang.g:3666:1: ruleYForInStatement returns [EObject current=null] : (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) ) ;
    public final EObject ruleYForInStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_body_4_0 = null;



        	enterRule();

        try {
            // InternalLang.g:3672:2: ( (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) ) )
            // InternalLang.g:3673:2: (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) )
            {
            // InternalLang.g:3673:2: (otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) ) )
            // InternalLang.g:3674:3: otherlv_0= 'for' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'in' ( (otherlv_3= RULE_ID ) ) ( (lv_body_4_0= ruleYBlock ) )
            {
            otherlv_0=(Token)match(input,69,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYForInStatementAccess().getForKeyword_0());
              		
            }
            // InternalLang.g:3678:3: ( (otherlv_1= RULE_ID ) )
            // InternalLang.g:3679:4: (otherlv_1= RULE_ID )
            {
            // InternalLang.g:3679:4: (otherlv_1= RULE_ID )
            // InternalLang.g:3680:5: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYForInStatementRule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_61); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getYForInStatementAccess().getItemYPropertyCrossReference_1_0());
              				
            }

            }


            }

            otherlv_2=(Token)match(input,70,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYForInStatementAccess().getInKeyword_2());
              		
            }
            // InternalLang.g:3695:3: ( (otherlv_3= RULE_ID ) )
            // InternalLang.g:3696:4: (otherlv_3= RULE_ID )
            {
            // InternalLang.g:3696:4: (otherlv_3= RULE_ID )
            // InternalLang.g:3697:5: otherlv_3= RULE_ID
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

            // InternalLang.g:3708:3: ( (lv_body_4_0= ruleYBlock ) )
            // InternalLang.g:3709:4: (lv_body_4_0= ruleYBlock )
            {
            // InternalLang.g:3709:4: (lv_body_4_0= ruleYBlock )
            // InternalLang.g:3710:5: lv_body_4_0= ruleYBlock
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
    // InternalLang.g:3731:1: entryRuleYAnnot returns [EObject current=null] : iv_ruleYAnnot= ruleYAnnot EOF ;
    public final EObject entryRuleYAnnot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnot = null;


        try {
            // InternalLang.g:3731:47: (iv_ruleYAnnot= ruleYAnnot EOF )
            // InternalLang.g:3732:2: iv_ruleYAnnot= ruleYAnnot EOF
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
    // InternalLang.g:3738:1: ruleYAnnot returns [EObject current=null] : ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) ) ;
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
            // InternalLang.g:3744:2: ( ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) ) )
            // InternalLang.g:3745:2: ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) )
            {
            // InternalLang.g:3745:2: ( () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) ) )
            // InternalLang.g:3746:3: () ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) )
            {
            // InternalLang.g:3746:3: ()
            // InternalLang.g:3747:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAccess().getYAnnotAction_0(),
              					current);
              			
            }

            }

            // InternalLang.g:3753:3: ( ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) ) )
            // InternalLang.g:3754:4: ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) )
            {
            // InternalLang.g:3754:4: ( (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax ) )
            // InternalLang.g:3755:5: (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax )
            {
            // InternalLang.g:3755:5: (lv_type_1_1= ruleYAnnotLength | lv_type_1_2= ruleYAnnotDecimal | lv_type_1_3= ruleYAnnotAction | lv_type_1_4= ruleYAnnotMessage | lv_type_1_5= ruleYAnnotMsgType | lv_type_1_6= ruleYAnnotDefault | lv_type_1_7= ruleYAnnotMax )
            int alt61=7;
            switch ( input.LA(1) ) {
            case 71:
                {
                alt61=1;
                }
                break;
            case 73:
                {
                alt61=2;
                }
                break;
            case 75:
                {
                alt61=3;
                }
                break;
            case 76:
                {
                alt61=4;
                }
                break;
            case 77:
                {
                alt61=5;
                }
                break;
            case 74:
                {
                alt61=6;
                }
                break;
            case 72:
                {
                alt61=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // InternalLang.g:3756:6: lv_type_1_1= ruleYAnnotLength
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
                    // InternalLang.g:3772:6: lv_type_1_2= ruleYAnnotDecimal
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
                    // InternalLang.g:3788:6: lv_type_1_3= ruleYAnnotAction
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
                    // InternalLang.g:3804:6: lv_type_1_4= ruleYAnnotMessage
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
                    // InternalLang.g:3820:6: lv_type_1_5= ruleYAnnotMsgType
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
                    // InternalLang.g:3836:6: lv_type_1_6= ruleYAnnotDefault
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
                    // InternalLang.g:3852:6: lv_type_1_7= ruleYAnnotMax
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
    // InternalLang.g:3874:1: entryRuleYAnnotTop returns [EObject current=null] : iv_ruleYAnnotTop= ruleYAnnotTop EOF ;
    public final EObject entryRuleYAnnotTop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTop = null;


        try {
            // InternalLang.g:3874:50: (iv_ruleYAnnotTop= ruleYAnnotTop EOF )
            // InternalLang.g:3875:2: iv_ruleYAnnotTop= ruleYAnnotTop EOF
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
    // InternalLang.g:3881:1: ruleYAnnotTop returns [EObject current=null] : ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) ) ;
    public final EObject ruleYAnnotTop() throws RecognitionException {
        EObject current = null;

        EObject lv_type_0_1 = null;

        EObject lv_type_0_2 = null;

        EObject lv_type_0_3 = null;

        EObject lv_type_0_4 = null;

        EObject lv_type_0_5 = null;



        	enterRule();

        try {
            // InternalLang.g:3887:2: ( ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) ) )
            // InternalLang.g:3888:2: ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) )
            {
            // InternalLang.g:3888:2: ( ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) ) )
            // InternalLang.g:3889:3: ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) )
            {
            // InternalLang.g:3889:3: ( (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase ) )
            // InternalLang.g:3890:4: (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase )
            {
            // InternalLang.g:3890:4: (lv_type_0_1= ruleYAnnotEntity | lv_type_0_2= ruleYAnnotTechnicalDesign | lv_type_0_3= ruleYAnnotSwift | lv_type_0_4= ruleYAnnotJava | lv_type_0_5= ruleYAnnotDatabase )
            int alt62=5;
            switch ( input.LA(1) ) {
            case 78:
                {
                alt62=1;
                }
                break;
            case 84:
                {
                alt62=2;
                }
                break;
            case 103:
                {
                alt62=3;
                }
                break;
            case 101:
                {
                alt62=4;
                }
                break;
            case 105:
                {
                alt62=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // InternalLang.g:3891:5: lv_type_0_1= ruleYAnnotEntity
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
                    // InternalLang.g:3907:5: lv_type_0_2= ruleYAnnotTechnicalDesign
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
                    // InternalLang.g:3923:5: lv_type_0_3= ruleYAnnotSwift
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
                    // InternalLang.g:3939:5: lv_type_0_4= ruleYAnnotJava
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
                    // InternalLang.g:3955:5: lv_type_0_5= ruleYAnnotDatabase
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
    // InternalLang.g:3976:1: entryRuleYAnnotLength returns [EObject current=null] : iv_ruleYAnnotLength= ruleYAnnotLength EOF ;
    public final EObject entryRuleYAnnotLength() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotLength = null;


        try {
            // InternalLang.g:3976:53: (iv_ruleYAnnotLength= ruleYAnnotLength EOF )
            // InternalLang.g:3977:2: iv_ruleYAnnotLength= ruleYAnnotLength EOF
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
    // InternalLang.g:3983:1: ruleYAnnotLength returns [EObject current=null] : ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotLength() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_length_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:3989:2: ( ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) )
            // InternalLang.g:3990:2: ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            {
            // InternalLang.g:3990:2: ( () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            // InternalLang.g:3991:3: () otherlv_1= '@length' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')'
            {
            // InternalLang.g:3991:3: ()
            // InternalLang.g:3992:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotLengthAccess().getYAnnotLengthAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,71,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotLengthAccess().getLengthKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotLengthAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4006:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4007:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4007:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4008:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_33); if (state.failed) return current;
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
    // InternalLang.g:4032:1: entryRuleYAnnotMax returns [EObject current=null] : iv_ruleYAnnotMax= ruleYAnnotMax EOF ;
    public final EObject entryRuleYAnnotMax() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMax = null;


        try {
            // InternalLang.g:4032:50: (iv_ruleYAnnotMax= ruleYAnnotMax EOF )
            // InternalLang.g:4033:2: iv_ruleYAnnotMax= ruleYAnnotMax EOF
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
    // InternalLang.g:4039:1: ruleYAnnotMax returns [EObject current=null] : ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotMax() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_length_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:4045:2: ( ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' ) )
            // InternalLang.g:4046:2: ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4046:2: ( () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')' )
            // InternalLang.g:4047:3: () otherlv_1= '@max' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ')'
            {
            // InternalLang.g:4047:3: ()
            // InternalLang.g:4048:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotMaxAccess().getYAnnotMaxAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,72,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMaxAccess().getMaxKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotMaxAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4062:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4063:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4063:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4064:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_33); if (state.failed) return current;
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
    // InternalLang.g:4088:1: entryRuleYAnnotDecimal returns [EObject current=null] : iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF ;
    public final EObject entryRuleYAnnotDecimal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDecimal = null;


        try {
            // InternalLang.g:4088:54: (iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF )
            // InternalLang.g:4089:2: iv_ruleYAnnotDecimal= ruleYAnnotDecimal EOF
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
    // InternalLang.g:4095:1: ruleYAnnotDecimal returns [EObject current=null] : ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
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
            // InternalLang.g:4101:2: ( ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalLang.g:4102:2: ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalLang.g:4102:2: ( () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalLang.g:4103:3: () otherlv_1= '@decimal' otherlv_2= '(' ( (lv_length_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_decimal_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalLang.g:4103:3: ()
            // InternalLang.g:4104:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDecimalAccess().getYAnnotDecimalAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,73,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDecimalAccess().getDecimalKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotDecimalAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4118:3: ( (lv_length_3_0= RULE_INT ) )
            // InternalLang.g:4119:4: (lv_length_3_0= RULE_INT )
            {
            // InternalLang.g:4119:4: (lv_length_3_0= RULE_INT )
            // InternalLang.g:4120:5: lv_length_3_0= RULE_INT
            {
            lv_length_3_0=(Token)match(input,RULE_INT,FOLLOW_63); if (state.failed) return current;
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

            otherlv_4=(Token)match(input,24,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotDecimalAccess().getCommaKeyword_4());
              		
            }
            // InternalLang.g:4140:3: ( (lv_decimal_5_0= RULE_INT ) )
            // InternalLang.g:4141:4: (lv_decimal_5_0= RULE_INT )
            {
            // InternalLang.g:4141:4: (lv_decimal_5_0= RULE_INT )
            // InternalLang.g:4142:5: lv_decimal_5_0= RULE_INT
            {
            lv_decimal_5_0=(Token)match(input,RULE_INT,FOLLOW_33); if (state.failed) return current;
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
    // InternalLang.g:4166:1: entryRuleYAnnotDefault returns [EObject current=null] : iv_ruleYAnnotDefault= ruleYAnnotDefault EOF ;
    public final EObject entryRuleYAnnotDefault() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefault = null;


        try {
            // InternalLang.g:4166:54: (iv_ruleYAnnotDefault= ruleYAnnotDefault EOF )
            // InternalLang.g:4167:2: iv_ruleYAnnotDefault= ruleYAnnotDefault EOF
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
    // InternalLang.g:4173:1: ruleYAnnotDefault returns [EObject current=null] : ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotDefault() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4179:2: ( ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' ) )
            // InternalLang.g:4180:2: ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4180:2: ( () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')' )
            // InternalLang.g:4181:3: () otherlv_1= '@default' otherlv_2= '(' ( (lv_type_3_0= ruleYAnnotDefaultType ) ) otherlv_4= ')'
            {
            // InternalLang.g:4181:3: ()
            // InternalLang.g:4182:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDefaultAccess().getYAnnotDefaultAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,74,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDefaultAccess().getDefaultKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotDefaultAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4196:3: ( (lv_type_3_0= ruleYAnnotDefaultType ) )
            // InternalLang.g:4197:4: (lv_type_3_0= ruleYAnnotDefaultType )
            {
            // InternalLang.g:4197:4: (lv_type_3_0= ruleYAnnotDefaultType )
            // InternalLang.g:4198:5: lv_type_3_0= ruleYAnnotDefaultType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotDefaultAccess().getTypeYAnnotDefaultTypeParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_33);
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
    // InternalLang.g:4223:1: entryRuleYAnnotDefaultType returns [EObject current=null] : iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF ;
    public final EObject entryRuleYAnnotDefaultType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultType = null;


        try {
            // InternalLang.g:4223:58: (iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF )
            // InternalLang.g:4224:2: iv_ruleYAnnotDefaultType= ruleYAnnotDefaultType EOF
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
    // InternalLang.g:4230:1: ruleYAnnotDefaultType returns [EObject current=null] : (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber ) ;
    public final EObject ruleYAnnotDefaultType() throws RecognitionException {
        EObject current = null;

        EObject this_YAnnotDefaultText_0 = null;

        EObject this_YAnnotDefaultNumber_1 = null;



        	enterRule();

        try {
            // InternalLang.g:4236:2: ( (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber ) )
            // InternalLang.g:4237:2: (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber )
            {
            // InternalLang.g:4237:2: (this_YAnnotDefaultText_0= ruleYAnnotDefaultText | this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==RULE_STRING) ) {
                alt63=1;
            }
            else if ( (LA63_0==RULE_INT) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // InternalLang.g:4238:3: this_YAnnotDefaultText_0= ruleYAnnotDefaultText
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
                    // InternalLang.g:4247:3: this_YAnnotDefaultNumber_1= ruleYAnnotDefaultNumber
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
    // InternalLang.g:4259:1: entryRuleYAnnotDefaultText returns [EObject current=null] : iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF ;
    public final EObject entryRuleYAnnotDefaultText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultText = null;


        try {
            // InternalLang.g:4259:58: (iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF )
            // InternalLang.g:4260:2: iv_ruleYAnnotDefaultText= ruleYAnnotDefaultText EOF
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
    // InternalLang.g:4266:1: ruleYAnnotDefaultText returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleYAnnotDefaultText() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalLang.g:4272:2: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // InternalLang.g:4273:2: ( (lv_value_0_0= RULE_STRING ) )
            {
            // InternalLang.g:4273:2: ( (lv_value_0_0= RULE_STRING ) )
            // InternalLang.g:4274:3: (lv_value_0_0= RULE_STRING )
            {
            // InternalLang.g:4274:3: (lv_value_0_0= RULE_STRING )
            // InternalLang.g:4275:4: lv_value_0_0= RULE_STRING
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
    // InternalLang.g:4294:1: entryRuleYAnnotDefaultNumber returns [EObject current=null] : iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF ;
    public final EObject entryRuleYAnnotDefaultNumber() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDefaultNumber = null;


        try {
            // InternalLang.g:4294:60: (iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF )
            // InternalLang.g:4295:2: iv_ruleYAnnotDefaultNumber= ruleYAnnotDefaultNumber EOF
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
    // InternalLang.g:4301:1: ruleYAnnotDefaultNumber returns [EObject current=null] : ( (lv_value_0_0= RULE_INT ) ) ;
    public final EObject ruleYAnnotDefaultNumber() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalLang.g:4307:2: ( ( (lv_value_0_0= RULE_INT ) ) )
            // InternalLang.g:4308:2: ( (lv_value_0_0= RULE_INT ) )
            {
            // InternalLang.g:4308:2: ( (lv_value_0_0= RULE_INT ) )
            // InternalLang.g:4309:3: (lv_value_0_0= RULE_INT )
            {
            // InternalLang.g:4309:3: (lv_value_0_0= RULE_INT )
            // InternalLang.g:4310:4: lv_value_0_0= RULE_INT
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
    // InternalLang.g:4329:1: entryRuleYAnnotAction returns [EObject current=null] : iv_ruleYAnnotAction= ruleYAnnotAction EOF ;
    public final EObject entryRuleYAnnotAction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAction = null;


        try {
            // InternalLang.g:4329:53: (iv_ruleYAnnotAction= ruleYAnnotAction EOF )
            // InternalLang.g:4330:2: iv_ruleYAnnotAction= ruleYAnnotAction EOF
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
    // InternalLang.g:4336:1: ruleYAnnotAction returns [EObject current=null] : ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_action_3_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4342:2: ( ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' ) )
            // InternalLang.g:4343:2: ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4343:2: ( () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')' )
            // InternalLang.g:4344:3: () otherlv_1= '@action' otherlv_2= '(' ( (lv_action_3_0= ruleValidID ) ) otherlv_4= ')'
            {
            // InternalLang.g:4344:3: ()
            // InternalLang.g:4345:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotActionAccess().getYAnnotActionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,75,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotActionAccess().getActionKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotActionAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4359:3: ( (lv_action_3_0= ruleValidID ) )
            // InternalLang.g:4360:4: (lv_action_3_0= ruleValidID )
            {
            // InternalLang.g:4360:4: (lv_action_3_0= ruleValidID )
            // InternalLang.g:4361:5: lv_action_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotActionAccess().getActionValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_33);
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
    // InternalLang.g:4386:1: entryRuleYAnnotMessage returns [EObject current=null] : iv_ruleYAnnotMessage= ruleYAnnotMessage EOF ;
    public final EObject entryRuleYAnnotMessage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMessage = null;


        try {
            // InternalLang.g:4386:54: (iv_ruleYAnnotMessage= ruleYAnnotMessage EOF )
            // InternalLang.g:4387:2: iv_ruleYAnnotMessage= ruleYAnnotMessage EOF
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
    // InternalLang.g:4393:1: ruleYAnnotMessage returns [EObject current=null] : ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' ) ;
    public final EObject ruleYAnnotMessage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_msg_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:4399:2: ( ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' ) )
            // InternalLang.g:4400:2: ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' )
            {
            // InternalLang.g:4400:2: ( () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')' )
            // InternalLang.g:4401:3: () otherlv_1= '@message' otherlv_2= '(' ( (lv_msg_3_0= RULE_STRING ) ) otherlv_4= ')'
            {
            // InternalLang.g:4401:3: ()
            // InternalLang.g:4402:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotMessageAccess().getYAnnotMessageAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,76,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMessageAccess().getMessageKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_65); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotMessageAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:4416:3: ( (lv_msg_3_0= RULE_STRING ) )
            // InternalLang.g:4417:4: (lv_msg_3_0= RULE_STRING )
            {
            // InternalLang.g:4417:4: (lv_msg_3_0= RULE_STRING )
            // InternalLang.g:4418:5: lv_msg_3_0= RULE_STRING
            {
            lv_msg_3_0=(Token)match(input,RULE_STRING,FOLLOW_33); if (state.failed) return current;
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
    // InternalLang.g:4442:1: entryRuleYAnnotMsgType returns [EObject current=null] : iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF ;
    public final EObject entryRuleYAnnotMsgType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotMsgType = null;


        try {
            // InternalLang.g:4442:54: (iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF )
            // InternalLang.g:4443:2: iv_ruleYAnnotMsgType= ruleYAnnotMsgType EOF
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
    // InternalLang.g:4449:1: ruleYAnnotMsgType returns [EObject current=null] : (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' ) ;
    public final EObject ruleYAnnotMsgType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_msgtype_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:4455:2: ( (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' ) )
            // InternalLang.g:4456:2: (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' )
            {
            // InternalLang.g:4456:2: (otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')' )
            // InternalLang.g:4457:3: otherlv_0= '@msgtype' otherlv_1= '(' ( (lv_msgtype_2_0= ruleValidID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,77,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAnnotMsgTypeAccess().getMsgtypeKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotMsgTypeAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalLang.g:4465:3: ( (lv_msgtype_2_0= ruleValidID ) )
            // InternalLang.g:4466:4: (lv_msgtype_2_0= ruleValidID )
            {
            // InternalLang.g:4466:4: (lv_msgtype_2_0= ruleValidID )
            // InternalLang.g:4467:5: lv_msgtype_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotMsgTypeAccess().getMsgtypeValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_33);
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
    // InternalLang.g:4492:1: entryRuleYAnnotEntityInner returns [EObject current=null] : iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF ;
    public final EObject entryRuleYAnnotEntityInner() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotEntityInner = null;


        try {
            // InternalLang.g:4492:58: (iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF )
            // InternalLang.g:4493:2: iv_ruleYAnnotEntityInner= ruleYAnnotEntityInner EOF
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
    // InternalLang.g:4499:1: ruleYAnnotEntityInner returns [EObject current=null] : (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId ) ;
    public final EObject ruleYAnnotEntityInner() throws RecognitionException {
        EObject current = null;

        EObject this_YAnnotAttr_0 = null;

        EObject this_YAnnotRel_1 = null;

        EObject this_YAnnotId_2 = null;



        	enterRule();

        try {
            // InternalLang.g:4505:2: ( (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId ) )
            // InternalLang.g:4506:2: (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId )
            {
            // InternalLang.g:4506:2: (this_YAnnotAttr_0= ruleYAnnotAttr | this_YAnnotRel_1= ruleYAnnotRel | this_YAnnotId_2= ruleYAnnotId )
            int alt64=3;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt64=1;
                }
                break;
            case 80:
                {
                alt64=2;
                }
                break;
            case 83:
                {
                alt64=3;
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
                    // InternalLang.g:4507:3: this_YAnnotAttr_0= ruleYAnnotAttr
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
                    // InternalLang.g:4516:3: this_YAnnotRel_1= ruleYAnnotRel
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
                    // InternalLang.g:4525:3: this_YAnnotId_2= ruleYAnnotId
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
    // InternalLang.g:4537:1: entryRuleYAnnotEntity returns [EObject current=null] : iv_ruleYAnnotEntity= ruleYAnnotEntity EOF ;
    public final EObject entryRuleYAnnotEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotEntity = null;


        try {
            // InternalLang.g:4537:53: (iv_ruleYAnnotEntity= ruleYAnnotEntity EOF )
            // InternalLang.g:4538:2: iv_ruleYAnnotEntity= ruleYAnnotEntity EOF
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
    // InternalLang.g:4544:1: ruleYAnnotEntity returns [EObject current=null] : (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' ) ;
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
            // InternalLang.g:4550:2: ( (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' ) )
            // InternalLang.g:4551:2: (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' )
            {
            // InternalLang.g:4551:2: (otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}' )
            // InternalLang.g:4552:3: otherlv_0= '@entity' ( (lv_name_1_0= ruleValidID ) ) (otherlv_2= ':' ( ( ruleQualifiedName ) ) )? otherlv_4= '{' ( (lv_annots_5_0= ruleYAnnotEntityInner ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,78,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getYAnnotEntityAccess().getEntityKeyword_0());
              		
            }
            // InternalLang.g:4556:3: ( (lv_name_1_0= ruleValidID ) )
            // InternalLang.g:4557:4: (lv_name_1_0= ruleValidID )
            {
            // InternalLang.g:4557:4: (lv_name_1_0= ruleValidID )
            // InternalLang.g:4558:5: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotEntityAccess().getNameValidIDParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_66);
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

            // InternalLang.g:4575:3: (otherlv_2= ':' ( ( ruleQualifiedName ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==17) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalLang.g:4576:4: otherlv_2= ':' ( ( ruleQualifiedName ) )
                    {
                    otherlv_2=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAnnotEntityAccess().getColonKeyword_2_0());
                      			
                    }
                    // InternalLang.g:4580:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:4581:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:4581:5: ( ruleQualifiedName )
                    // InternalLang.g:4582:6: ruleQualifiedName
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

            otherlv_4=(Token)match(input,19,FOLLOW_67); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotEntityAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:4601:3: ( (lv_annots_5_0= ruleYAnnotEntityInner ) )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( ((LA66_0>=79 && LA66_0<=80)||LA66_0==83) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalLang.g:4602:4: (lv_annots_5_0= ruleYAnnotEntityInner )
            	    {
            	    // InternalLang.g:4602:4: (lv_annots_5_0= ruleYAnnotEntityInner )
            	    // InternalLang.g:4603:5: lv_annots_5_0= ruleYAnnotEntityInner
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotEntityAccess().getAnnotsYAnnotEntityInnerParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_67);
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
            	    break loop66;
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
    // InternalLang.g:4628:1: entryRuleYAnnotAttr returns [EObject current=null] : iv_ruleYAnnotAttr= ruleYAnnotAttr EOF ;
    public final EObject entryRuleYAnnotAttr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAttr = null;


        try {
            // InternalLang.g:4628:51: (iv_ruleYAnnotAttr= ruleYAnnotAttr EOF )
            // InternalLang.g:4629:2: iv_ruleYAnnotAttr= ruleYAnnotAttr EOF
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
    // InternalLang.g:4635:1: ruleYAnnotAttr returns [EObject current=null] : ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? ) ;
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
            // InternalLang.g:4641:2: ( ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? ) )
            // InternalLang.g:4642:2: ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? )
            {
            // InternalLang.g:4642:2: ( () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )? )
            // InternalLang.g:4643:3: () otherlv_1= '@attr' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= ':' ( ( ruleQualifiedName ) ) ( (lv_optional_5_0= '?' ) )? ( (lv_annots_6_0= ruleYAnnot ) )* (otherlv_7= ';' )?
            {
            // InternalLang.g:4643:3: ()
            // InternalLang.g:4644:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAttrAccess().getYAnnotAttrAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,79,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotAttrAccess().getAttrKeyword_1());
              		
            }
            // InternalLang.g:4654:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:4655:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:4655:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:4656:5: lv_name_2_0= ruleValidID
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
            // InternalLang.g:4677:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:4678:4: ( ruleQualifiedName )
            {
            // InternalLang.g:4678:4: ( ruleQualifiedName )
            // InternalLang.g:4679:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotAttrRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotAttrAccess().getYclassYClassCrossReference_4_0());
              				
            }
            pushFollow(FOLLOW_68);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4693:3: ( (lv_optional_5_0= '?' ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==22) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalLang.g:4694:4: (lv_optional_5_0= '?' )
                    {
                    // InternalLang.g:4694:4: (lv_optional_5_0= '?' )
                    // InternalLang.g:4695:5: lv_optional_5_0= '?'
                    {
                    lv_optional_5_0=(Token)match(input,22,FOLLOW_69); if (state.failed) return current;
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

            // InternalLang.g:4707:3: ( (lv_annots_6_0= ruleYAnnot ) )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( ((LA68_0>=71 && LA68_0<=77)) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // InternalLang.g:4708:4: (lv_annots_6_0= ruleYAnnot )
            	    {
            	    // InternalLang.g:4708:4: (lv_annots_6_0= ruleYAnnot )
            	    // InternalLang.g:4709:5: lv_annots_6_0= ruleYAnnot
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotAttrAccess().getAnnotsYAnnotParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_69);
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
            	    break loop68;
                }
            } while (true);

            // InternalLang.g:4726:3: (otherlv_7= ';' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==12) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalLang.g:4727:4: otherlv_7= ';'
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
    // InternalLang.g:4736:1: entryRuleYAnnotRel returns [EObject current=null] : iv_ruleYAnnotRel= ruleYAnnotRel EOF ;
    public final EObject entryRuleYAnnotRel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotRel = null;


        try {
            // InternalLang.g:4736:50: (iv_ruleYAnnotRel= ruleYAnnotRel EOF )
            // InternalLang.g:4737:2: iv_ruleYAnnotRel= ruleYAnnotRel EOF
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
    // InternalLang.g:4743:1: ruleYAnnotRel returns [EObject current=null] : ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? ) ;
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
            // InternalLang.g:4749:2: ( ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? ) )
            // InternalLang.g:4750:2: ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? )
            {
            // InternalLang.g:4750:2: ( () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )? )
            // InternalLang.g:4751:3: () otherlv_1= '@rel' ( (lv_parent_2_0= '@parent' ) )? ( (lv_name_3_0= ruleValidID ) ) ( (lv_optional_4_0= '?' ) )? otherlv_5= '->' ( ( ruleQualifiedName ) ) ( (lv_many_7_0= '*' ) )? (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )? (otherlv_10= ';' )?
            {
            // InternalLang.g:4751:3: ()
            // InternalLang.g:4752:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotRelAccess().getYAnnotRelAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,80,FOLLOW_70); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotRelAccess().getRelKeyword_1());
              		
            }
            // InternalLang.g:4762:3: ( (lv_parent_2_0= '@parent' ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==81) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalLang.g:4763:4: (lv_parent_2_0= '@parent' )
                    {
                    // InternalLang.g:4763:4: (lv_parent_2_0= '@parent' )
                    // InternalLang.g:4764:5: lv_parent_2_0= '@parent'
                    {
                    lv_parent_2_0=(Token)match(input,81,FOLLOW_3); if (state.failed) return current;
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

            // InternalLang.g:4776:3: ( (lv_name_3_0= ruleValidID ) )
            // InternalLang.g:4777:4: (lv_name_3_0= ruleValidID )
            {
            // InternalLang.g:4777:4: (lv_name_3_0= ruleValidID )
            // InternalLang.g:4778:5: lv_name_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotRelAccess().getNameValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_71);
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

            // InternalLang.g:4795:3: ( (lv_optional_4_0= '?' ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==22) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // InternalLang.g:4796:4: (lv_optional_4_0= '?' )
                    {
                    // InternalLang.g:4796:4: (lv_optional_4_0= '?' )
                    // InternalLang.g:4797:5: lv_optional_4_0= '?'
                    {
                    lv_optional_4_0=(Token)match(input,22,FOLLOW_58); if (state.failed) return current;
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
            // InternalLang.g:4813:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:4814:4: ( ruleQualifiedName )
            {
            // InternalLang.g:4814:4: ( ruleQualifiedName )
            // InternalLang.g:4815:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotRelRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotRelAccess().getTargetYAnnotEntityCrossReference_6_0());
              				
            }
            pushFollow(FOLLOW_72);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalLang.g:4829:3: ( (lv_many_7_0= '*' ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==44) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalLang.g:4830:4: (lv_many_7_0= '*' )
                    {
                    // InternalLang.g:4830:4: (lv_many_7_0= '*' )
                    // InternalLang.g:4831:5: lv_many_7_0= '*'
                    {
                    lv_many_7_0=(Token)match(input,44,FOLLOW_73); if (state.failed) return current;
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

            // InternalLang.g:4843:3: (otherlv_8= '<-' ( ( ruleQualifiedName ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==82) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalLang.g:4844:4: otherlv_8= '<-' ( ( ruleQualifiedName ) )
                    {
                    otherlv_8=(Token)match(input,82,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getYAnnotRelAccess().getLessThanSignHyphenMinusKeyword_8_0());
                      			
                    }
                    // InternalLang.g:4848:4: ( ( ruleQualifiedName ) )
                    // InternalLang.g:4849:5: ( ruleQualifiedName )
                    {
                    // InternalLang.g:4849:5: ( ruleQualifiedName )
                    // InternalLang.g:4850:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotRelRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getYAnnotRelAccess().getInverseYAnnotRelCrossReference_8_1_0());
                      					
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


                    }
                    break;

            }

            // InternalLang.g:4865:3: (otherlv_10= ';' )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==12) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalLang.g:4866:4: otherlv_10= ';'
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
    // InternalLang.g:4875:1: entryRuleYAnnotId returns [EObject current=null] : iv_ruleYAnnotId= ruleYAnnotId EOF ;
    public final EObject entryRuleYAnnotId() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotId = null;


        try {
            // InternalLang.g:4875:49: (iv_ruleYAnnotId= ruleYAnnotId EOF )
            // InternalLang.g:4876:2: iv_ruleYAnnotId= ruleYAnnotId EOF
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
    // InternalLang.g:4882:1: ruleYAnnotId returns [EObject current=null] : ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? ) ;
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
            // InternalLang.g:4888:2: ( ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? ) )
            // InternalLang.g:4889:2: ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? )
            {
            // InternalLang.g:4889:2: ( () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )? )
            // InternalLang.g:4890:3: () otherlv_1= '@id' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' (otherlv_8= ';' )?
            {
            // InternalLang.g:4890:3: ()
            // InternalLang.g:4891:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotIdAccess().getYAnnotIdAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,83,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotIdAccess().getIdKeyword_1());
              		
            }
            // InternalLang.g:4901:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:4902:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:4902:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:4903:5: lv_name_2_0= ruleValidID
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
            // InternalLang.g:4924:3: ( (otherlv_4= RULE_ID ) )
            // InternalLang.g:4925:4: (otherlv_4= RULE_ID )
            {
            // InternalLang.g:4925:4: (otherlv_4= RULE_ID )
            // InternalLang.g:4926:5: otherlv_4= RULE_ID
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

            // InternalLang.g:4937:3: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==24) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // InternalLang.g:4938:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
            	    {
            	    otherlv_5=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getYAnnotIdAccess().getCommaKeyword_5_0());
            	      			
            	    }
            	    // InternalLang.g:4942:4: ( (otherlv_6= RULE_ID ) )
            	    // InternalLang.g:4943:5: (otherlv_6= RULE_ID )
            	    {
            	    // InternalLang.g:4943:5: (otherlv_6= RULE_ID )
            	    // InternalLang.g:4944:6: otherlv_6= RULE_ID
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
            	    break loop75;
                }
            } while (true);

            otherlv_7=(Token)match(input,28,FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYAnnotIdAccess().getRightParenthesisKeyword_6());
              		
            }
            // InternalLang.g:4960:3: (otherlv_8= ';' )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==12) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalLang.g:4961:4: otherlv_8= ';'
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
    // InternalLang.g:4970:1: entryRuleYAnnotTechnicalDesign returns [EObject current=null] : iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF ;
    public final EObject entryRuleYAnnotTechnicalDesign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTechnicalDesign = null;


        try {
            // InternalLang.g:4970:62: (iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF )
            // InternalLang.g:4971:2: iv_ruleYAnnotTechnicalDesign= ruleYAnnotTechnicalDesign EOF
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
    // InternalLang.g:4977:1: ruleYAnnotTechnicalDesign returns [EObject current=null] : ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' ) ;
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
            // InternalLang.g:4983:2: ( ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' ) )
            // InternalLang.g:4984:2: ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' )
            {
            // InternalLang.g:4984:2: ( () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}' )
            // InternalLang.g:4985:3: () otherlv_1= '@td' otherlv_2= 'database' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleYAnnotTable ) )* otherlv_6= '}'
            {
            // InternalLang.g:4985:3: ()
            // InternalLang.g:4986:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotTechnicalDesignAccess().getYAnnotTechnicalDesignAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,84,FOLLOW_75); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotTechnicalDesignAccess().getTdKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,85,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotTechnicalDesignAccess().getDatabaseKeyword_2());
              		
            }
            // InternalLang.g:5000:3: ( (otherlv_3= RULE_ID ) )
            // InternalLang.g:5001:4: (otherlv_3= RULE_ID )
            {
            // InternalLang.g:5001:4: (otherlv_3= RULE_ID )
            // InternalLang.g:5002:5: otherlv_3= RULE_ID
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

            otherlv_4=(Token)match(input,19,FOLLOW_76); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getYAnnotTechnicalDesignAccess().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalLang.g:5017:3: ( (lv_features_5_0= ruleYAnnotTable ) )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==86) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // InternalLang.g:5018:4: (lv_features_5_0= ruleYAnnotTable )
            	    {
            	    // InternalLang.g:5018:4: (lv_features_5_0= ruleYAnnotTable )
            	    // InternalLang.g:5019:5: lv_features_5_0= ruleYAnnotTable
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTechnicalDesignAccess().getFeaturesYAnnotTableParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_76);
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
            	    break loop77;
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
    // InternalLang.g:5044:1: entryRuleYAnnotTable returns [EObject current=null] : iv_ruleYAnnotTable= ruleYAnnotTable EOF ;
    public final EObject entryRuleYAnnotTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotTable = null;


        try {
            // InternalLang.g:5044:52: (iv_ruleYAnnotTable= ruleYAnnotTable EOF )
            // InternalLang.g:5045:2: iv_ruleYAnnotTable= ruleYAnnotTable EOF
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
    // InternalLang.g:5051:1: ruleYAnnotTable returns [EObject current=null] : ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' ) ;
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
            // InternalLang.g:5057:2: ( ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' ) )
            // InternalLang.g:5058:2: ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' )
            {
            // InternalLang.g:5058:2: ( () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}' )
            // InternalLang.g:5059:3: () otherlv_1= '@table' ( (lv_name_2_0= ruleValidID ) ) otherlv_3= '->' ( ( ruleQualifiedName ) ) otherlv_5= '{' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )* ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )? ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )* otherlv_9= '}'
            {
            // InternalLang.g:5059:3: ()
            // InternalLang.g:5060:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotTableAccess().getYAnnotTableAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,86,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotTableAccess().getTableKeyword_1());
              		
            }
            // InternalLang.g:5070:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5071:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5071:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5072:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotTableAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_58);
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
            // InternalLang.g:5093:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5094:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5094:4: ( ruleQualifiedName )
            // InternalLang.g:5095:5: ruleQualifiedName
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

            otherlv_5=(Token)match(input,19,FOLLOW_77); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getYAnnotTableAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalLang.g:5113:3: ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==98) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // InternalLang.g:5114:4: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    {
            	    // InternalLang.g:5114:4: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    // InternalLang.g:5115:5: lv_columns_6_0= ruleYAnnotAbstractColumn
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getColumnsYAnnotAbstractColumnParserRuleCall_6_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_77);
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
            	    break loop78;
                }
            } while (true);

            // InternalLang.g:5132:3: ( (lv_primarykey_7_0= ruleYAnnotPrimaryKey ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==99) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalLang.g:5133:4: (lv_primarykey_7_0= ruleYAnnotPrimaryKey )
                    {
                    // InternalLang.g:5133:4: (lv_primarykey_7_0= ruleYAnnotPrimaryKey )
                    // InternalLang.g:5134:5: lv_primarykey_7_0= ruleYAnnotPrimaryKey
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getPrimarykeyYAnnotPrimaryKeyParserRuleCall_7_0());
                      				
                    }
                    pushFollow(FOLLOW_78);
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

            // InternalLang.g:5151:3: ( (lv_foreignkeys_8_0= ruleYAnnotForeignKey ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==100) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalLang.g:5152:4: (lv_foreignkeys_8_0= ruleYAnnotForeignKey )
            	    {
            	    // InternalLang.g:5152:4: (lv_foreignkeys_8_0= ruleYAnnotForeignKey )
            	    // InternalLang.g:5153:5: lv_foreignkeys_8_0= ruleYAnnotForeignKey
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getYAnnotTableAccess().getForeignkeysYAnnotForeignKeyParserRuleCall_8_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_78);
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
            	    break loop80;
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
    // InternalLang.g:5178:1: entryRuleYAnnotColumn returns [EObject current=null] : iv_ruleYAnnotColumn= ruleYAnnotColumn EOF ;
    public final EObject entryRuleYAnnotColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotColumn = null;


        try {
            // InternalLang.g:5178:53: (iv_ruleYAnnotColumn= ruleYAnnotColumn EOF )
            // InternalLang.g:5179:2: iv_ruleYAnnotColumn= ruleYAnnotColumn EOF
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
    // InternalLang.g:5185:1: ruleYAnnotColumn returns [EObject current=null] : ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? ) ;
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
            // InternalLang.g:5191:2: ( ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? ) )
            // InternalLang.g:5192:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? )
            {
            // InternalLang.g:5192:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )? )
            // InternalLang.g:5193:3: () otherlv_1= '->' ( ( ruleQualifiedName ) ) otherlv_3= 'as' ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) ) ( (lv_optional_5_0= '?' ) )? ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )* (otherlv_7= ';' )?
            {
            // InternalLang.g:5193:3: ()
            // InternalLang.g:5194:4: 
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
            // InternalLang.g:5204:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5205:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5205:4: ( ruleQualifiedName )
            // InternalLang.g:5206:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotColumnRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAttrrefYAnnotAttrCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_79);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,87,FOLLOW_80); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotColumnAccess().getAsKeyword_3());
              		
            }
            // InternalLang.g:5224:3: ( ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) ) )
            // InternalLang.g:5225:4: ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) )
            {
            // InternalLang.g:5225:4: ( (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' ) )
            // InternalLang.g:5226:5: (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' )
            {
            // InternalLang.g:5226:5: (lv_type_4_1= 'VARCHAR' | lv_type_4_2= 'CHAR' | lv_type_4_3= 'INTEGER' | lv_type_4_4= 'BIGINT' | lv_type_4_5= 'SMALLINT' | lv_type_4_6= 'DECIMAL' | lv_type_4_7= 'TIME' | lv_type_4_8= 'DATE' | lv_type_4_9= 'TIMESTAMP' | lv_type_4_10= 'BOOLEAN' )
            int alt81=10;
            switch ( input.LA(1) ) {
            case 88:
                {
                alt81=1;
                }
                break;
            case 89:
                {
                alt81=2;
                }
                break;
            case 90:
                {
                alt81=3;
                }
                break;
            case 91:
                {
                alt81=4;
                }
                break;
            case 92:
                {
                alt81=5;
                }
                break;
            case 93:
                {
                alt81=6;
                }
                break;
            case 94:
                {
                alt81=7;
                }
                break;
            case 95:
                {
                alt81=8;
                }
                break;
            case 96:
                {
                alt81=9;
                }
                break;
            case 97:
                {
                alt81=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }

            switch (alt81) {
                case 1 :
                    // InternalLang.g:5227:6: lv_type_4_1= 'VARCHAR'
                    {
                    lv_type_4_1=(Token)match(input,88,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5238:6: lv_type_4_2= 'CHAR'
                    {
                    lv_type_4_2=(Token)match(input,89,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5249:6: lv_type_4_3= 'INTEGER'
                    {
                    lv_type_4_3=(Token)match(input,90,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5260:6: lv_type_4_4= 'BIGINT'
                    {
                    lv_type_4_4=(Token)match(input,91,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5271:6: lv_type_4_5= 'SMALLINT'
                    {
                    lv_type_4_5=(Token)match(input,92,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5282:6: lv_type_4_6= 'DECIMAL'
                    {
                    lv_type_4_6=(Token)match(input,93,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5293:6: lv_type_4_7= 'TIME'
                    {
                    lv_type_4_7=(Token)match(input,94,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5304:6: lv_type_4_8= 'DATE'
                    {
                    lv_type_4_8=(Token)match(input,95,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5315:6: lv_type_4_9= 'TIMESTAMP'
                    {
                    lv_type_4_9=(Token)match(input,96,FOLLOW_81); if (state.failed) return current;
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
                    // InternalLang.g:5326:6: lv_type_4_10= 'BOOLEAN'
                    {
                    lv_type_4_10=(Token)match(input,97,FOLLOW_81); if (state.failed) return current;
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

            // InternalLang.g:5339:3: ( (lv_optional_5_0= '?' ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==22) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalLang.g:5340:4: (lv_optional_5_0= '?' )
                    {
                    // InternalLang.g:5340:4: (lv_optional_5_0= '?' )
                    // InternalLang.g:5341:5: lv_optional_5_0= '?'
                    {
                    lv_optional_5_0=(Token)match(input,22,FOLLOW_82); if (state.failed) return current;
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

            // InternalLang.g:5353:3: ( ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) ) )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==71||LA84_0==73) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // InternalLang.g:5354:4: ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) )
            	    {
            	    // InternalLang.g:5354:4: ( (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal ) )
            	    // InternalLang.g:5355:5: (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal )
            	    {
            	    // InternalLang.g:5355:5: (lv_annots_6_1= ruleYAnnotLength | lv_annots_6_2= ruleYAnnotDecimal )
            	    int alt83=2;
            	    int LA83_0 = input.LA(1);

            	    if ( (LA83_0==71) ) {
            	        alt83=1;
            	    }
            	    else if ( (LA83_0==73) ) {
            	        alt83=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 83, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt83) {
            	        case 1 :
            	            // InternalLang.g:5356:6: lv_annots_6_1= ruleYAnnotLength
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAnnotsYAnnotLengthParserRuleCall_6_0_0());
            	              					
            	            }
            	            pushFollow(FOLLOW_82);
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
            	            // InternalLang.g:5372:6: lv_annots_6_2= ruleYAnnotDecimal
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getYAnnotColumnAccess().getAnnotsYAnnotDecimalParserRuleCall_6_0_1());
            	              					
            	            }
            	            pushFollow(FOLLOW_82);
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
            	    break loop84;
                }
            } while (true);

            // InternalLang.g:5390:3: (otherlv_7= ';' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==12) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalLang.g:5391:4: otherlv_7= ';'
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
    // InternalLang.g:5400:1: entryRuleYAnnotColumnLike returns [EObject current=null] : iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF ;
    public final EObject entryRuleYAnnotColumnLike() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotColumnLike = null;


        try {
            // InternalLang.g:5400:57: (iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF )
            // InternalLang.g:5401:2: iv_ruleYAnnotColumnLike= ruleYAnnotColumnLike EOF
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
    // InternalLang.g:5407:1: ruleYAnnotColumnLike returns [EObject current=null] : ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? ) ;
    public final EObject ruleYAnnotColumnLike() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalLang.g:5413:2: ( ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? ) )
            // InternalLang.g:5414:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? )
            {
            // InternalLang.g:5414:2: ( () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )? )
            // InternalLang.g:5415:3: () otherlv_1= '->' ( ( ruleQualifiedName ) ) (otherlv_3= ';' )?
            {
            // InternalLang.g:5415:3: ()
            // InternalLang.g:5416:4: 
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
            // InternalLang.g:5426:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5427:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5427:4: ( ruleQualifiedName )
            // InternalLang.g:5428:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getYAnnotColumnLikeRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotColumnLikeAccess().getColumnrefYAnnotAbstractColumnCrossReference_2_0());
              				
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

            // InternalLang.g:5442:3: (otherlv_3= ';' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==12) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalLang.g:5443:4: otherlv_3= ';'
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
    // InternalLang.g:5452:1: entryRuleYAnnotAbstractColumn returns [EObject current=null] : iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF ;
    public final EObject entryRuleYAnnotAbstractColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotAbstractColumn = null;


        try {
            // InternalLang.g:5452:61: (iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF )
            // InternalLang.g:5453:2: iv_ruleYAnnotAbstractColumn= ruleYAnnotAbstractColumn EOF
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
    // InternalLang.g:5459:1: ruleYAnnotAbstractColumn returns [EObject current=null] : ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) ) ;
    public final EObject ruleYAnnotAbstractColumn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_3_1 = null;

        EObject lv_type_3_2 = null;



        	enterRule();

        try {
            // InternalLang.g:5465:2: ( ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) ) )
            // InternalLang.g:5466:2: ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) )
            {
            // InternalLang.g:5466:2: ( () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) ) )
            // InternalLang.g:5467:3: () otherlv_1= '@column' ( (lv_name_2_0= ruleValidID ) ) ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) )
            {
            // InternalLang.g:5467:3: ()
            // InternalLang.g:5468:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotAbstractColumnAccess().getYAnnotAbstractColumnAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,98,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotAbstractColumnAccess().getColumnKeyword_1());
              		
            }
            // InternalLang.g:5478:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5479:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5479:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5480:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotAbstractColumnAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_58);
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

            // InternalLang.g:5497:3: ( ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) ) )
            // InternalLang.g:5498:4: ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) )
            {
            // InternalLang.g:5498:4: ( (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike ) )
            // InternalLang.g:5499:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )
            {
            // InternalLang.g:5499:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )
            int alt87=2;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // InternalLang.g:5500:6: lv_type_3_1= ruleYAnnotColumn
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
                    // InternalLang.g:5516:6: lv_type_3_2= ruleYAnnotColumnLike
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
    // InternalLang.g:5538:1: entryRuleYAnnotPrimaryKey returns [EObject current=null] : iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF ;
    public final EObject entryRuleYAnnotPrimaryKey() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotPrimaryKey = null;


        try {
            // InternalLang.g:5538:57: (iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF )
            // InternalLang.g:5539:2: iv_ruleYAnnotPrimaryKey= ruleYAnnotPrimaryKey EOF
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
    // InternalLang.g:5545:1: ruleYAnnotPrimaryKey returns [EObject current=null] : ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? ) ;
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
            // InternalLang.g:5551:2: ( ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? ) )
            // InternalLang.g:5552:2: ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? )
            {
            // InternalLang.g:5552:2: ( () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )? )
            // InternalLang.g:5553:3: () otherlv_1= '@primary' otherlv_2= '(' ( (otherlv_3= RULE_ID ) )* (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* otherlv_6= ')' (otherlv_7= ';' )?
            {
            // InternalLang.g:5553:3: ()
            // InternalLang.g:5554:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotPrimaryKeyAccess().getYAnnotPrimaryKeyAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,99,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotPrimaryKeyAccess().getPrimaryKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,27,FOLLOW_83); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotPrimaryKeyAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalLang.g:5568:3: ( (otherlv_3= RULE_ID ) )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==RULE_ID) ) {
                    alt88=1;
                }


                switch (alt88) {
            	case 1 :
            	    // InternalLang.g:5569:4: (otherlv_3= RULE_ID )
            	    {
            	    // InternalLang.g:5569:4: (otherlv_3= RULE_ID )
            	    // InternalLang.g:5570:5: otherlv_3= RULE_ID
            	    {
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElement(grammarAccess.getYAnnotPrimaryKeyRule());
            	      					}
            	      				
            	    }
            	    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_83); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_3, grammarAccess.getYAnnotPrimaryKeyAccess().getColumnsYAnnotAbstractColumnCrossReference_3_0());
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop88;
                }
            } while (true);

            // InternalLang.g:5581:3: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==24) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // InternalLang.g:5582:4: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,24,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_4, grammarAccess.getYAnnotPrimaryKeyAccess().getCommaKeyword_4_0());
            	      			
            	    }
            	    // InternalLang.g:5586:4: ( (otherlv_5= RULE_ID ) )
            	    // InternalLang.g:5587:5: (otherlv_5= RULE_ID )
            	    {
            	    // InternalLang.g:5587:5: (otherlv_5= RULE_ID )
            	    // InternalLang.g:5588:6: otherlv_5= RULE_ID
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
            	    break loop89;
                }
            } while (true);

            otherlv_6=(Token)match(input,28,FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getYAnnotPrimaryKeyAccess().getRightParenthesisKeyword_5());
              		
            }
            // InternalLang.g:5604:3: (otherlv_7= ';' )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==12) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalLang.g:5605:4: otherlv_7= ';'
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
    // InternalLang.g:5614:1: entryRuleYAnnotForeignKey returns [EObject current=null] : iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF ;
    public final EObject entryRuleYAnnotForeignKey() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotForeignKey = null;


        try {
            // InternalLang.g:5614:57: (iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF )
            // InternalLang.g:5615:2: iv_ruleYAnnotForeignKey= ruleYAnnotForeignKey EOF
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
    // InternalLang.g:5621:1: ruleYAnnotForeignKey returns [EObject current=null] : ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? ) ;
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
            // InternalLang.g:5627:2: ( ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? ) )
            // InternalLang.g:5628:2: ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? )
            {
            // InternalLang.g:5628:2: ( () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )? )
            // InternalLang.g:5629:3: () otherlv_1= '@foreign' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) ) (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )* otherlv_7= '}' (otherlv_8= ';' )?
            {
            // InternalLang.g:5629:3: ()
            // InternalLang.g:5630:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotForeignKeyAccess().getYAnnotForeignKeyAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,100,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotForeignKeyAccess().getForeignKeyword_1());
              		
            }
            // InternalLang.g:5640:3: ( ( ruleQualifiedName ) )
            // InternalLang.g:5641:4: ( ruleQualifiedName )
            {
            // InternalLang.g:5641:4: ( ruleQualifiedName )
            // InternalLang.g:5642:5: ruleQualifiedName
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

            otherlv_3=(Token)match(input,19,FOLLOW_84); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getYAnnotForeignKeyAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalLang.g:5660:3: ( (lv_columns_4_0= ruleYAnnotAbstractColumn ) )
            // InternalLang.g:5661:4: (lv_columns_4_0= ruleYAnnotAbstractColumn )
            {
            // InternalLang.g:5661:4: (lv_columns_4_0= ruleYAnnotAbstractColumn )
            // InternalLang.g:5662:5: lv_columns_4_0= ruleYAnnotAbstractColumn
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotForeignKeyAccess().getColumnsYAnnotAbstractColumnParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_85);
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

            // InternalLang.g:5679:3: (otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) ) )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==24) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // InternalLang.g:5680:4: otherlv_5= ',' ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )
            	    {
            	    otherlv_5=(Token)match(input,24,FOLLOW_84); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getYAnnotForeignKeyAccess().getCommaKeyword_5_0());
            	      			
            	    }
            	    // InternalLang.g:5684:4: ( (lv_columns_6_0= ruleYAnnotAbstractColumn ) )
            	    // InternalLang.g:5685:5: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    {
            	    // InternalLang.g:5685:5: (lv_columns_6_0= ruleYAnnotAbstractColumn )
            	    // InternalLang.g:5686:6: lv_columns_6_0= ruleYAnnotAbstractColumn
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getYAnnotForeignKeyAccess().getColumnsYAnnotAbstractColumnParserRuleCall_5_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_85);
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
            	    break loop91;
                }
            } while (true);

            otherlv_7=(Token)match(input,20,FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getYAnnotForeignKeyAccess().getRightCurlyBracketKeyword_6());
              		
            }
            // InternalLang.g:5708:3: (otherlv_8= ';' )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==12) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalLang.g:5709:4: otherlv_8= ';'
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
    // InternalLang.g:5718:1: entryRuleYAnnotJava returns [EObject current=null] : iv_ruleYAnnotJava= ruleYAnnotJava EOF ;
    public final EObject entryRuleYAnnotJava() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotJava = null;


        try {
            // InternalLang.g:5718:51: (iv_ruleYAnnotJava= ruleYAnnotJava EOF )
            // InternalLang.g:5719:2: iv_ruleYAnnotJava= ruleYAnnotJava EOF
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
    // InternalLang.g:5725:1: ruleYAnnotJava returns [EObject current=null] : ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleYAnnotJava() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalLang.g:5731:2: ( ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalLang.g:5732:2: ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalLang.g:5732:2: ( () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalLang.g:5733:3: () otherlv_1= '@java' (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            // InternalLang.g:5733:3: ()
            // InternalLang.g:5734:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotJavaAccess().getYAnnotJavaAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,101,FOLLOW_86); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotJavaAccess().getJavaKeyword_1());
              		
            }
            // InternalLang.g:5744:3: (otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==102) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalLang.g:5745:4: otherlv_2= 'uses' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,102,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getYAnnotJavaAccess().getUsesKeyword_2_0());
                      			
                    }
                    // InternalLang.g:5749:4: ( (otherlv_3= RULE_ID ) )
                    // InternalLang.g:5750:5: (otherlv_3= RULE_ID )
                    {
                    // InternalLang.g:5750:5: (otherlv_3= RULE_ID )
                    // InternalLang.g:5751:6: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotJavaRule());
                      						}
                      					
                    }
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_3, grammarAccess.getYAnnotJavaAccess().getDatabaseYAnnotDatabaseCrossReference_2_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:5763:3: (otherlv_4= ';' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==12) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalLang.g:5764:4: otherlv_4= ';'
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
    // InternalLang.g:5773:1: entryRuleYAnnotSwift returns [EObject current=null] : iv_ruleYAnnotSwift= ruleYAnnotSwift EOF ;
    public final EObject entryRuleYAnnotSwift() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotSwift = null;


        try {
            // InternalLang.g:5773:52: (iv_ruleYAnnotSwift= ruleYAnnotSwift EOF )
            // InternalLang.g:5774:2: iv_ruleYAnnotSwift= ruleYAnnotSwift EOF
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
    // InternalLang.g:5780:1: ruleYAnnotSwift returns [EObject current=null] : ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? ) ;
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
            // InternalLang.g:5786:2: ( ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? ) )
            // InternalLang.g:5787:2: ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? )
            {
            // InternalLang.g:5787:2: ( () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )? )
            // InternalLang.g:5788:3: () otherlv_1= '@swift' otherlv_2= 'module' ( (lv_name_3_0= ruleValidID ) ) (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )? (otherlv_6= ';' )?
            {
            // InternalLang.g:5788:3: ()
            // InternalLang.g:5789:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotSwiftAccess().getYAnnotSwiftAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,103,FOLLOW_87); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotSwiftAccess().getSwiftKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,104,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getYAnnotSwiftAccess().getModuleKeyword_2());
              		
            }
            // InternalLang.g:5803:3: ( (lv_name_3_0= ruleValidID ) )
            // InternalLang.g:5804:4: (lv_name_3_0= ruleValidID )
            {
            // InternalLang.g:5804:4: (lv_name_3_0= ruleValidID )
            // InternalLang.g:5805:5: lv_name_3_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotSwiftAccess().getNameValidIDParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_86);
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

            // InternalLang.g:5822:3: (otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==102) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalLang.g:5823:4: otherlv_4= 'uses' ( (otherlv_5= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,102,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getYAnnotSwiftAccess().getUsesKeyword_4_0());
                      			
                    }
                    // InternalLang.g:5827:4: ( (otherlv_5= RULE_ID ) )
                    // InternalLang.g:5828:5: (otherlv_5= RULE_ID )
                    {
                    // InternalLang.g:5828:5: (otherlv_5= RULE_ID )
                    // InternalLang.g:5829:6: otherlv_5= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getYAnnotSwiftRule());
                      						}
                      					
                    }
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_5, grammarAccess.getYAnnotSwiftAccess().getDatabaseYAnnotDatabaseCrossReference_4_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalLang.g:5841:3: (otherlv_6= ';' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==12) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalLang.g:5842:4: otherlv_6= ';'
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
    // InternalLang.g:5851:1: entryRuleYAnnotDatabase returns [EObject current=null] : iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF ;
    public final EObject entryRuleYAnnotDatabase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleYAnnotDatabase = null;


        try {
            // InternalLang.g:5851:55: (iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF )
            // InternalLang.g:5852:2: iv_ruleYAnnotDatabase= ruleYAnnotDatabase EOF
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
    // InternalLang.g:5858:1: ruleYAnnotDatabase returns [EObject current=null] : ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? ) ;
    public final EObject ruleYAnnotDatabase() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalLang.g:5864:2: ( ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? ) )
            // InternalLang.g:5865:2: ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? )
            {
            // InternalLang.g:5865:2: ( () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )? )
            // InternalLang.g:5866:3: () otherlv_1= '@database' ( (lv_name_2_0= ruleValidID ) ) (otherlv_3= ';' )?
            {
            // InternalLang.g:5866:3: ()
            // InternalLang.g:5867:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getYAnnotDatabaseAccess().getYAnnotDatabaseAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,105,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getYAnnotDatabaseAccess().getDatabaseKeyword_1());
              		
            }
            // InternalLang.g:5877:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalLang.g:5878:4: (lv_name_2_0= ruleValidID )
            {
            // InternalLang.g:5878:4: (lv_name_2_0= ruleValidID )
            // InternalLang.g:5879:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getYAnnotDatabaseAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_74);
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

            // InternalLang.g:5896:3: (otherlv_3= ';' )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==12) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalLang.g:5897:4: otherlv_3= ';'
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
    // InternalLang.g:5906:1: ruleYAccessLevel returns [Enumerator current=null] : ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) ) ;
    public final Enumerator ruleYAccessLevel() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalLang.g:5912:2: ( ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) ) )
            // InternalLang.g:5913:2: ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) )
            {
            // InternalLang.g:5913:2: ( (enumLiteral_0= 'private' ) | (enumLiteral_1= 'protected' ) | (enumLiteral_2= 'public' ) )
            int alt98=3;
            switch ( input.LA(1) ) {
            case 106:
                {
                alt98=1;
                }
                break;
            case 107:
                {
                alt98=2;
                }
                break;
            case 108:
                {
                alt98=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // InternalLang.g:5914:3: (enumLiteral_0= 'private' )
                    {
                    // InternalLang.g:5914:3: (enumLiteral_0= 'private' )
                    // InternalLang.g:5915:4: enumLiteral_0= 'private'
                    {
                    enumLiteral_0=(Token)match(input,106,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getYAccessLevelAccess().getPRIVATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getYAccessLevelAccess().getPRIVATEEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalLang.g:5922:3: (enumLiteral_1= 'protected' )
                    {
                    // InternalLang.g:5922:3: (enumLiteral_1= 'protected' )
                    // InternalLang.g:5923:4: enumLiteral_1= 'protected'
                    {
                    enumLiteral_1=(Token)match(input,107,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getYAccessLevelAccess().getPROTECTEDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getYAccessLevelAccess().getPROTECTEDEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalLang.g:5930:3: (enumLiteral_2= 'public' )
                    {
                    // InternalLang.g:5930:3: (enumLiteral_2= 'public' )
                    // InternalLang.g:5931:4: enumLiteral_2= 'public'
                    {
                    enumLiteral_2=(Token)match(input,108,FOLLOW_2); if (state.failed) return current;
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
        // InternalLang.g:1359:5: ( 'else' )
        // InternalLang.g:1359:6: 'else'
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


    protected DFA27 dfa27 = new DFA27(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA87 dfa87 = new DFA87(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\4\1\14\1\uffff\1\4\15\uffff";
    static final String dfa_3s = "\1\105\1\35\1\uffff\1\71\15\uffff";
    static final String dfa_4s = "\2\uffff\1\2\1\uffff\1\4\1\5\1\6\1\7\1\11\1\12\1\13\1\14\1\15\1\16\1\1\1\10\1\3";
    static final String dfa_5s = "\21\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\2\13\27\uffff\1\2\1\14\1\uffff\1\15\15\uffff\6\13\1\3\5\uffff\1\4\2\uffff\1\5\1\7\1\6\2\uffff\1\10\1\11\1\12",
            "\1\13\1\uffff\1\13\2\uffff\1\16\13\uffff\1\13",
            "",
            "\1\20\64\uffff\1\17",
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
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1046:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YReturn_1= ruleYReturn | this_YReadStatement_2= ruleYReadStatement | this_YCreateStatement_3= ruleYCreateStatement | this_YUpdateStatement_4= ruleYUpdateStatement | this_YAssociateStatement_5= ruleYAssociateStatement | this_YDeleteStatement_6= ruleYDeleteStatement | this_YReadEachStatement_7= ruleYReadEachStatement | this_YWhileStatement_8= ruleYWhileStatement | this_YRepeatWhileStatement_9= ruleYRepeatWhileStatement | this_YForInStatement_10= ruleYForInStatement | (this_YExpression_11= ruleYExpression otherlv_12= ';' ) | this_YIfStatement_13= ruleYIfStatement | this_YSwitchStatement_14= ruleYSwitchStatement )";
        }
    }
    static final String dfa_7s = "\10\uffff";
    static final String dfa_8s = "\3\uffff\1\5\3\uffff\1\5";
    static final String dfa_9s = "\1\4\1\21\1\4\1\16\1\4\2\uffff\1\16";
    static final String dfa_10s = "\1\4\1\21\1\4\1\35\1\4\2\uffff\1\35";
    static final String dfa_11s = "\5\uffff\1\2\1\1\1\uffff";
    static final String dfa_12s = "\10\uffff}>";
    static final String[] dfa_13s = {
            "\1\1",
            "\1\2",
            "\1\3",
            "\1\4\16\uffff\1\6",
            "\1\7",
            "",
            "",
            "\1\4\16\uffff\1\6"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1582:2: (this_YVariableDeclaration_0= ruleYVariableDeclaration | this_YParameter_1= ruleYParameter )";
        }
    }
    static final String dfa_14s = "\7\uffff";
    static final String dfa_15s = "\2\uffff\1\4\3\uffff\1\4";
    static final String dfa_16s = "\1\22\1\4\1\14\1\4\2\uffff\1\14";
    static final String dfa_17s = "\1\22\1\4\1\144\1\4\2\uffff\1\144";
    static final String dfa_18s = "\4\uffff\1\2\1\1\1\uffff";
    static final String dfa_19s = "\7\uffff}>";
    static final String[] dfa_20s = {
            "\1\1",
            "\1\2",
            "\1\4\1\uffff\1\3\5\uffff\1\4\3\uffff\1\4\76\uffff\1\5\12\uffff\3\4",
            "\1\6",
            "",
            "",
            "\1\4\1\uffff\1\3\5\uffff\1\4\3\uffff\1\4\76\uffff\1\5\12\uffff\3\4"
    };

    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[][] dfa_20 = unpackEncodedStringArray(dfa_20s);

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = dfa_14;
            this.eof = dfa_15;
            this.min = dfa_16;
            this.max = dfa_17;
            this.accept = dfa_18;
            this.special = dfa_19;
            this.transition = dfa_20;
        }
        public String getDescription() {
            return "5499:5: (lv_type_3_1= ruleYAnnotColumn | lv_type_3_2= ruleYAnnotColumnLike )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000013002L,0x000002A000104000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000012002L,0x000002A000104000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010002L,0x000002A000104000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004310000L,0x00001C0000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000004300000L,0x00001C0000000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000C41000L,0x0000000000003F80L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000441000L,0x0000000000003F80L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000041000L,0x0000000000003F80L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000001000L,0x0000000000003F80L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000002000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000010000010L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000011000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000000C0010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xC83F8002C0100070L,0x0000000000000039L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x001FC00008000070L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x001FC00008001070L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x001F800000000070L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000C00120000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000008004002L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x001FC00018000070L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000030002800002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0040000001000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0400000001000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0400000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000100000L,0x0000000000098000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000401002L,0x0000000000003F80L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000000001002L,0x0000000000003F80L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000000010L,0x0000000000020000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000100000001002L,0x0000000000040000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000001002L,0x0000000000040000L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000100000L,0x0000000000400000L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000100000L,0x0000001C00000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000100000L,0x0000001000000000L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000000L,0x00000003FF000000L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000401002L,0x0000000000000280L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000001002L,0x0000000000000280L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000011000010L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000001100000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000001002L,0x0000004000000000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});

}