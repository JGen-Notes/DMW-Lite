package eu.jgen.notes.dmw.lite.generator

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.Notifier

class OutputConfigurationAdapter implements Adapter {
	String outputConfigurationName

	new(String outputConfigurationName) {
		this.outputConfigurationName = outputConfigurationName
	}

	def String getOutputConfigurationName() {
		return outputConfigurationName
	}

	override void notifyChanged(Notification notification) {
	}

	override Notifier getTarget() {
		return null
	}

	override void setTarget(Notifier newTarget) {
	}

	override boolean isAdapterForType(Object type) {
		return false
	}
}
