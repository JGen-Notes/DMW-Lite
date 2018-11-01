/*
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.ui.wizard;

import org.eclipse.xtext.ui.wizard.XtextNewProjectWizard;

import org.eclipse.xtext.ui.wizard.IExtendedProjectInfo;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import com.google.inject.Inject;

public class LangNewProjectWizard extends XtextNewProjectWizard {

	private LangWizardNewProjectCreationPage mainPage;

	@Inject
	public LangNewProjectWizard(IProjectCreator projectCreator) {
		super(projectCreator);
		setWindowTitle("New Lang Project");
	}

	protected LangWizardNewProjectCreationPage getMainPage() {
		return mainPage;
	}

	/**
	 * Use this method to add pages to the wizard.
	 * The one-time generated version of this class will add a default new project page to the wizard.
	 */
	@Override
	public void addPages() {
		mainPage = createMainPage("basicNewProjectPage");
		mainPage.setTitle("Lang Project");
		mainPage.setDescription("Create a new Lang project.");
		addPage(mainPage);
	}

	protected LangWizardNewProjectCreationPage createMainPage(String pageName) {
		return new LangWizardNewProjectCreationPage(pageName);
	}

	/**
	 * Use this method to read the project settings from the wizard pages and feed them into the project info class.
	 */
	@Override
	protected IExtendedProjectInfo getProjectInfo() {
		LangProjectInfo projectInfo = new LangProjectInfo();
		projectInfo.setProjectName(mainPage.getProjectName());
		if (!mainPage.useDefaults()) {
			projectInfo.setLocationPath(mainPage.getLocationPath());
		}
		return projectInfo;
	}

}
