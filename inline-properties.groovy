import org.commonjava.maven.ext.core.groovy.PMEBaseScript
import org.commonjava.maven.ext.core.groovy.PMEInvocationPoint
import org.commonjava.maven.ext.core.groovy.InvocationStage
import org.commonjava.maven.ext.core.groovy.BaseScript
import org.commonjava.maven.atlas.ident.ref.SimpleProjectRef;
import org.commonjava.maven.ext.common.model.Project;
import org.commonjava.maven.ext.common.util.PropertyResolver;

@PMEInvocationPoint(invocationPoint = InvocationStage.FIRST)
@PMEBaseScript BaseScript pme


String hadoop = "\${hadoop.identifier}"
String newVersion =  PropertyResolver.resolvePropertiesUnchecked( getSession(), pme.getProject().getInheritedList(), hadoop )

for ( Project p : pme.getProjects() ) {
    System.out.println ("#### Atttempting to change project " + p)

    if ( p.getModel().getVersion().contains(hadoop) ) {
        p.getModel().setVersion( p.getModel().getVersion().replace (hadoop, newVersion) );
    }
}
