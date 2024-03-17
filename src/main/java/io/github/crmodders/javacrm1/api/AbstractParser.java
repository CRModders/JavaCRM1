package io.github.crmodders.javacrm1.api;

public abstract class AbstractParser {
    public final String crmVersion;

    public AbstractParser(String crmVersion) {
        this.crmVersion = crmVersion;
    }

    public abstract RepoSpec parse(String hjson);
}
