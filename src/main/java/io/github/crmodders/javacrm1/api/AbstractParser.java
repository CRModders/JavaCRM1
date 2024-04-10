package io.github.crmodders.javacrm1.api;

public abstract class AbstractParser {
    public final int crmVersion;

    public AbstractParser(int crmVersion) {
        this.crmVersion = crmVersion;
    }

    public abstract IRepoSpec parse(String hjson);
}
