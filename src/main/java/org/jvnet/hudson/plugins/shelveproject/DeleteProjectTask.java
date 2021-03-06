package org.jvnet.hudson.plugins.shelveproject;

import hudson.model.Label;
import hudson.model.Node;
import hudson.model.Queue;
import hudson.model.ResourceList;
import hudson.model.queue.CauseOfBlockage;
import hudson.model.queue.SubTask;
import hudson.security.ACL;
import org.acegisecurity.Authentication;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DeleteProjectTask
    implements Queue.FlyweightTask, Queue.TransientTask
{
    private final String[] shelvedProjectArchiveNames;

    public DeleteProjectTask(String[] shelvedProjectArchiveNames)
    {
        this.shelvedProjectArchiveNames = shelvedProjectArchiveNames;
    }

    public Label getAssignedLabel()
    {
        return null;
    }

    public Node getLastBuiltOn()
    {
        return null;
    }

    public boolean isBuildBlocked()
    {
        return false;
    }

    public String getWhyBlocked()
    {
        return null;
    }

    public CauseOfBlockage getCauseOfBlockage() {
        return null;
    }

    public String getName()
    {
        return "Deleting Project";
    }

    public String getFullDisplayName()
    {
        return getName();
    }

    public long getEstimatedDuration()
    {
        return -1;
    }

    public Queue.Executable createExecutable()
        throws IOException
    {
        return new DeleteProjectExecutable(this, shelvedProjectArchiveNames);
    }

    public Queue.Task getOwnerTask()
    {
        return this;
    }

    public Object getSameNodeConstraint()
    {
        return null;
    }

    public void checkAbortPermission()
    {
    }

    public boolean hasAbortPermission()
    {
        return false;
    }

    public String getUrl()
    {
        return null;
    }

    public boolean isConcurrentBuild()
    {
        return false;
    }

    public Collection<? extends SubTask> getSubTasks()
    {
        final List<SubTask> subTasks = new LinkedList<SubTask>();
        subTasks.add(this);
        return subTasks;
    }

    public ResourceList getResourceList()
    {
        return new ResourceList();
    }

    public String getDisplayName()
    {
        return getName();
    }

    @Nonnull
    public Authentication getDefaultAuthentication() {
        return ACL.SYSTEM;
    }

}