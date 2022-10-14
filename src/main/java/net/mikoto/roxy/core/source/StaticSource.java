package net.mikoto.roxy.core.source;

/**
 * @author mikoto
 * @date 2022/6/18 17:11
 */
public class StaticSource<S> implements Source<S> {
    private S source;

    /**
     * Add a source.
     *
     * @param server The source.
     */
    @Override
    public void addServer(S server) {
        this.source = server;
    }

    /**
     * Get the sources.
     *
     * @return The sources.
     */
    @Override
    public Object[] getServers() {
        return new Object[]{source};
    }

    /**
     * Get the source.
     *
     * @return The source.
     */
    @Override
    public S getServer() {
        return source;
    }
}
