package controller;

public class ActionForward {
	private String path;
	private boolean redirect;
	public ActionForward() {

	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", redirect=" + redirect + "]";
	}
}
