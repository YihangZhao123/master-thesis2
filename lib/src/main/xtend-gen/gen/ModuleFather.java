package gen;

import java.util.HashSet;
import java.util.Set;
import template.Template;

@SuppressWarnings("all")
public class ModuleFather {
  protected Set<Template> templates;
  
  public ModuleFather() {
    HashSet<Template> _hashSet = new HashSet<Template>();
    this.templates = _hashSet;
  }
  
  public ModuleFather(final Template a) {
    HashSet<Template> _hashSet = new HashSet<Template>();
    this.templates = _hashSet;
    this.templates.add(a);
  }
  
  public ModuleFather(final Template a, final Template b) {
    HashSet<Template> _hashSet = new HashSet<Template>();
    this.templates = _hashSet;
    this.templates.add(a);
    this.templates.add(b);
  }
}
